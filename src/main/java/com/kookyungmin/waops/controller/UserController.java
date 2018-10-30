package com.kookyungmin.waops.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.kookyungmin.waops.auth.SnsLogin;
import com.kookyungmin.waops.auth.SnsValue;
import com.kookyungmin.waops.domain.LoginDTO;
import com.kookyungmin.waops.domain.User;
import com.kookyungmin.waops.interceptor.SessionNames;
import com.kookyungmin.waops.service.UserService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	@Inject
	private SnsValue naverSns;
	
	@Inject 
	private SnsValue googleSns;
	
	@Inject
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Inject
	private OAuth2Parameters googleOAuth2Parameters;
	
	@RequestMapping(value = "/auth/{snsService}/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String snsLoginCallback(Model model, 
								   @RequestParam String code,
								   @PathVariable String snsService,
								   HttpSession session) throws Exception {
		logger.debug("loginCallback>>>>>> service={}",snsService);
		
		try {
			SnsValue sns = null;
			if(StringUtils.equals("naver", snsService)) {
				sns = naverSns;
			}else {
				sns = googleSns;
			}
			
			//1. code를 이용해서 access_token 받기
			//2. access_token으로 profile 정보가져아기
			SnsLogin snsLogin = new SnsLogin(sns);
			User snsUser = snsLogin.getUserProfile(code);
			logger.debug("loginCallback>>>>> profile= {}" ,snsUser);
			//3. DB에 저장
			
			User user = service.getBySns(snsUser);
			
			if(user == null) {
				logger.debug("loginCallback>>>>>loginfail");
				model.addAttribute("loginStatus", "Failed");;
				return "redirect:/login";
			} else {
				logger.debug("loginCallback>>>>>loginsuccess, user={}", user);
				Date expire = new Date(System.currentTimeMillis() + SessionNames.EXPIRE * 1000);
				service.keepLogin(user.getUid(), session.getId(), expire);
				user.setUpw(null);
				session.setAttribute(SessionNames.LOGIN, user);
				//전에 접근 시도한 페이지가 있다면 그 페이지로 이동
				String attempted = (String)session.getAttribute(SessionNames.ATTEMPTED);
				logger.debug("LoginInterceptor.postHandle()>>>>>>attempted={}",attempted);
				if(StringUtils.isNotEmpty(attempted)) {
					session.removeAttribute(SessionNames.ATTEMPTED);
					return "redirect:" + attempted;
				}else {
					return "redirect:/questions/all";
				}
			}
		}catch(Exception e) {
			model.addAttribute("loginStatus", "Failed");
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @ModelAttribute("loginStatus") String loginStatus) {
		logger.debug("login>>>>>> {}", loginStatus);
		
		//네이버 auth url 가져옴 (직접 구현)
		SnsLogin snsLogin = new SnsLogin(naverSns);
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
				
		//구글 auth url 가져옴 (이미 구현됨)
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		model.addAttribute("google_url", url);
		return "login";
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, 
			              Model model,
			              HttpSession session) throws Exception {
		try {
			User user = service.login(dto);
			if(user != null) {
				//로그인 성공 시 세션 id 저장
				Date expire = new Date(System.currentTimeMillis() + SessionNames.EXPIRE * 1000);
				service.keepLogin(user.getUid(), session.getId(), expire);
				user.setUpw(null);
				model.addAttribute("user", user);
			} else {
				model.addAttribute("loginStatus", "Failed");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,
			             HttpServletRequest request,
			             HttpServletResponse response) throws Exception {
		logger.debug("logout()>>>>>");
		
		User user = (User)session.getAttribute(SessionNames.LOGIN);
		if(user != null) {
			service.keepLogin(user.getUid(), session.getId(), new Date());
			session.removeAttribute(SessionNames.LOGIN);
			//세션 비우기
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN_COOKIE);
			//쿠키 지우기
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
		}
		return "redirect:/login";
	}
}

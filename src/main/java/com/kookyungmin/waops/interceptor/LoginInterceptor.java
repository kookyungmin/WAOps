package com.kookyungmin.waops.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kookyungmin.waops.domain.User;

public class LoginInterceptor extends HandlerInterceptorAdapter implements SessionNames {
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		HttpSession session = request.getSession();
		logger.debug("LoginInterceptor.preHandle()>>>>>> session={}", session);
		//세션이 남아있다면 제거
		if(session.getAttribute(LOGIN) != null) {
			session.removeAttribute(LOGIN);
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
			               HttpServletResponse response,
			               Object handler, 
			               ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		logger.debug("LoginInterceptor.postHandle()>>>>>>model={}", modelAndView.getModel());
		Object user = modelAndView.getModelMap().get("user");
		//로그인 되었다면
		if(user != null) {
			session.setAttribute(LOGIN, user);
			//파라미터에 useCookie가 있다면 쿠키를 굽는다.
			if(StringUtils.isNotEmpty(request.getParameter("useCookie"))) {
				Cookie loginCookie = new Cookie(LOGIN_COOKIE, session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(7 * 24 * 60 * 60);
				response.addCookie(loginCookie);
			}
			
			//전에 접근 시도한 페이지가 있다면 그 페이지로 이동
			String attempted = (String)session.getAttribute(ATTEMPTED);
			logger.debug("LoginInterceptor.postHandle()>>>>>>attempted={}",attempted);
			if(StringUtils.isNotEmpty(attempted)) {
				session.removeAttribute(ATTEMPTED);
				response.sendRedirect(attempted);
			}else {
				response.sendRedirect("/questions/all");
			}
		}else {
			response.sendRedirect("/login?loginStatus=" + modelAndView.getModelMap().get("loginStatus"));
		}
	}
}

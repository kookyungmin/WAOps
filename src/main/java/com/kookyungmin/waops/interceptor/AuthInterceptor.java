package com.kookyungmin.waops.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.kookyungmin.waops.domain.User;
import com.kookyungmin.waops.service.UserService;

public class AuthInterceptor  extends HandlerInterceptorAdapter implements SessionNames {
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Inject
	private UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			                 Object handler) throws Exception {
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		String httpMethod = request.getMethod();
		logger.debug("AuthInterceptor.preHandle>>>> uri = {}, method = {}", uri, httpMethod);
		if(session.getAttribute(LOGIN) == null) {
			Cookie loginCookie = WebUtils.getCookie(request, SessionNames.LOGIN_COOKIE);
			if(loginCookie != null) {
				User loginUser = service.checkLoginBefore(loginCookie.getValue());
				if(loginUser != null) {
					session.setAttribute(LOGIN, loginUser);
					return true;
				}
				
			}
			saveAttemptedLocation(request, session);
			response.sendRedirect("/login");
		}
		return true;
	}
	private void saveAttemptedLocation(HttpServletRequest request, HttpSession session) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if (StringUtils.isNotEmpty(query))
			uri += "?" + query;
		session.setAttribute(ATTEMPTED, uri);
	}
}

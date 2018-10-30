package com.kookyungmin.waops.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kookyungmin.waops.domain.LoginDTO;
import com.kookyungmin.waops.domain.User;
import com.kookyungmin.waops.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	private UserDAO userDAO;

	@Override
	public User login(LoginDTO dto) throws Exception {
		return userDAO.login(dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date expire) throws Exception {
		userDAO.keepLogin(uid, sessionId, expire);
		
	}

	@Override
	public User checkLoginBefore(String loginCookie) throws Exception {
		return userDAO.checkLoginBefore(loginCookie);
	}

	@Override
	public User getBySns(User snsUser) throws Exception {
		return userDAO.getBySns(snsUser);
	}
	

}

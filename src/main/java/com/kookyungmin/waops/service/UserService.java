package com.kookyungmin.waops.service;

import java.util.Date;

import com.kookyungmin.waops.domain.LoginDTO;
import com.kookyungmin.waops.domain.User;

public interface UserService {
	public User login(LoginDTO dto) throws Exception;
	public void keepLogin(String uid, String sessionId, Date expire) throws Exception;
	public User checkLoginBefore(String loginCookie) throws Exception;
	public User getBySns(User snsUser) throws Exception;
}

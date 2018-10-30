package com.kookyungmin.waops.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.kookyungmin.waops.domain.LoginDTO;
import com.kookyungmin.waops.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserDAOImpl.class);
	private static final String NS = "LoginMapper";
	private static final String LOGIN = NS + ".login";
	private static final String KEEPLOGIN = NS + ".keepLogin";
	private static final String CHECKLOGINBEFORE = NS + ".checkUserWithSessionKey";
	private static final String GETBYSNSNAVER = NS + ".getBySnsNaver";
	private static final String GETBYSNSGOOGLE = NS + ".getBySnsGoogle";
	
	@Inject
	private SqlSession session;
	
	
	@Override
	public User login(LoginDTO dto) throws Exception {
		return session.selectOne(LOGIN, dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date expire) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("sessionId", sessionId);
		map.put("expire", expire);
		session.update(KEEPLOGIN, map);
	}

	@Override
	public User checkLoginBefore(String loginCookie) throws Exception {
		logger.debug("checkLoginBefore>>>{}", loginCookie);
		return session.selectOne(CHECKLOGINBEFORE, loginCookie);
	}

	@Override
	public User getBySns(User snsUser) throws Exception {
		if(StringUtils.isNotEmpty(snsUser.getNaverId())) {
			return session.selectOne(GETBYSNSNAVER, snsUser);
		}else {
			return session.selectOne(GETBYSNSGOOGLE, snsUser);
		}
	}
}

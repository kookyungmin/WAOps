package com.kookyungmin.waops.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class SqlSessionFactoryTest {
	private static Logger logger = LoggerFactory.getLogger(SqlSessionFactoryTest.class);
	
	@Inject
	private SqlSessionFactory sqlFactory;
	
	@Test
	public void testFactory() {
		logger.debug("testFactory>>>>{}",sqlFactory);
	}
	@Test
	public void testSession() {
		try(SqlSession session = sqlFactory.openSession()){
			logger.debug("testSession>>>{}", session);
		}catch(Exception e) {
			logger.debug(e.getMessage());
		}
	}
	
}

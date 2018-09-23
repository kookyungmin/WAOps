package com.kookyungmin.waops.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")

public class QuestionDAOTest {
	private static Logger logger = LoggerFactory.getLogger(QuestionDAOTest.class);
	private static int getTotalNum = 0;
	
	@Inject
	QuestionDAO questionDAO;
	
	@Ignore
	public void getTotalNumTest() throws Exception{
		getTotalNum = questionDAO.getTotalNum();
		logger.debug("getTotalNumTest>>> getTotalNum={}", getTotalNum);
	}
	@Ignore
	public void listPageTest() throws Exception{
		logger.debug("listPageTest>>>");
		Criteria cri = new Criteria();
		List<Question> list = questionDAO.listPage(cri);
		assertEquals(list.size(), getTotalNum);
	}
	@Ignore
	public void registerTest() throws Exception{
		logger.debug("registerTest>>>");
		Question question = new Question();
		question.setTitle("등록 테스트");
		question.setWriter("꾸리");
		question.setContent("예제 테스트입니다");
		question.setScore(5);
		int res = questionDAO.register(question);
		assertTrue(res == 1);
	}
	@Ignore
	public void readTest() throws Exception{
		logger.debug("readTest>>>");
		Question question = questionDAO.read(15);
		assertEquals(question.getTitle(), "ㅇㅇ");
		assertEquals(question.getWriter(), "ㅇㅇ");
		assertEquals(question.getScore(), 5);
	}
}

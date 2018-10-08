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
import com.kookyungmin.waops.domain.Reply;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")

public class QuestionReplyDAOTest {
	private static Logger logger = LoggerFactory.getLogger(QuestionReplyDAOTest.class);
	private static int getTotalCount = 0;
	
	@Inject
	ReplyDAO replyDAO;
	
	@Test
	public void getTotalNumTest() throws Exception{
		int qno = 1;
		getTotalCount = replyDAO.getTotalCount(1);
		logger.debug("getTotalNumTest>>> getTotalCount={}", getTotalCount);
	}
	@Test
	public void listPageTest() throws Exception{
		logger.debug("listPageTest>>>");
		Criteria cri = new Criteria();
		int qno = 1;
		List<Reply> list = replyDAO.listPage(qno, cri);
		//assertEquals(list.size(), getTotalCount);
	}
	
	@Ignore
	public void registerTest() throws Exception{
		logger.debug("registerTest>>>");
		Reply reply = new Reply();
		reply.setQno(118);
		reply.setReplyer("꾸리");
		reply.setReplytext("댓글테스트");
		int res = replyDAO.register(reply);
		assertTrue(res == 1);
	}
	@Ignore
	public void readTest() throws Exception{
		logger.debug("readTest>>>");
		Reply reply = replyDAO.read(1);
		assertEquals(reply.getReplyer(),"꾸리");
		assertEquals(reply.getReplytext(), "댓글테스트");
	}
	@Ignore
	public void deleteTest() throws Exception{
		logger.debug("deleteTest>>>");
		int res = replyDAO.delete(1);
		assertEquals(res, 1);
	}
	@Ignore
	public void updateTest() throws Exception{
		logger.debug("updateTest>>>");
		Reply reply = new Reply();
		reply.setRno(1);
		reply.setReplytext("수정테스트");
		int res = replyDAO.update(reply);
		assertTrue(res == 1);
	}
}

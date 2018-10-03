package com.kookyungmin.waops.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Reply;
import com.kookyungmin.waops.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	private static Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Override
	public int getTotalCount(int qno) throws Exception {
		logger.debug("ReplyServiceImpl.getTotalCount()>>> qno={}", qno);
		return replyDAO.getTotalCount(qno);
	}

	@Override
	public List<Reply> listPage(int qno, Criteria cri) throws Exception {
		logger.debug("ReplyServiceImpl.listPage()>>> qno={}, Criteria={}", qno, cri);
		return replyDAO.listPage(qno, cri);
	}

	@Override
	public Reply read(int rno) throws Exception {
		logger.debug("ReplyServiceImpl.read()>>> rno={}", rno);
		return replyDAO.read(rno);
	}

	@Override
	public int register(Reply reply) throws Exception {
		logger.debug("ReplyServiceImpl.register()>>> reply={}", reply);
		return replyDAO.register(reply);
	}

	@Override
	public int update(Reply reply) throws Exception {
		logger.debug("ReplyServiceImpl.update()>>> reply={}", reply);
		return replyDAO.update(reply);
	}

	@Override
	public int delete(int rno) throws Exception {
		logger.debug("ReplyServiceImpl.delete()>>> rno={}", rno);
		return replyDAO.delete(rno);
	}

}

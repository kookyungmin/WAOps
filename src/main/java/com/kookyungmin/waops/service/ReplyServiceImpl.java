package com.kookyungmin.waops.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Reply;
import com.kookyungmin.waops.persistence.QuestionDAO;
import com.kookyungmin.waops.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	private static Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private QuestionDAO questionDAO;
	
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
	
	@Transactional
	@Override
	public Map<String, Object> register(Reply reply) throws Exception {
		logger.debug("ReplyServiceImpl.register()>>> reply={}", reply);
		int response = 0;
		int resRegister = replyDAO.register(reply);
		int qno = reply.getQno();
		int resUpdateReplyCnt = questionDAO.updateReplyCnt(qno, 1);
		int lastPage = (replyDAO.getTotalCount(qno) - 1) / new Criteria().getPerPageNum() + 1;
		if(resRegister == 1 && resUpdateReplyCnt == 1) {
			response = 1;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("response", response);
		map.put("lastPage", lastPage);
		return map;
	}

	@Override
	public int update(Reply reply) throws Exception {
		logger.debug("ReplyServiceImpl.update()>>> reply={}", reply);
		return replyDAO.update(reply);
	}
	
	@Transactional
	@Override
	public int delete(int rno) throws Exception {
		logger.debug("ReplyServiceImpl.delete()>>> rno={}", rno);
		int response = 0;
		int resUpdateReplyCnt = questionDAO.updateReplyCnt(replyDAO.getQno(rno), -1);
		int resDelete = replyDAO.delete(rno);
		if(resDelete == 1 && resUpdateReplyCnt == 1) {
			response = 1;
		}
		return response;
	}

}

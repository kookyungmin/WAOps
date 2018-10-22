package com.kookyungmin.waops.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Question;
import com.kookyungmin.waops.persistence.QuestionDAO;
import com.kookyungmin.waops.persistence.ReplyDAO;

@Service
public class QuestionServiceImpl implements QuestionService{
	private static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	ReplyDAO replyDAO;
	
	@Override
	public List<Question> listPage(Criteria cri) throws Exception{
		logger.debug("QuestionServiceImpl.listPage()>>> Criteria={}",cri);
		return questionDAO.listPage(cri);
	}
	@Override
	public int register(Question question) throws Exception {
		logger.debug("QuestionServiceImpl.register()>>> Question={}",question);
		return questionDAO.register(question);
	}
	
	//대부분의 데이터베이스가 기본으로 사용하는 수준으로, 다른 연결이 커밋하지 않은 데이터는 볼 수 없도록 함
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public Question read(int qno) throws Exception {
		logger.debug("QuestionServiceImpl.read()>>> qno={}",qno);
		questionDAO.updateViewCnt(qno);
		return questionDAO.read(qno);
	}
	@Override
	public int delete(int qno) throws Exception {
		logger.debug("QuestionServiceImpl.delete()>>> qno={}",qno);
		replyDAO.deleteAll(qno);
		return questionDAO.delete(qno);
	}
	@Override
	public int update(Question question) throws Exception {
		logger.debug("QuestionServiceImpl.update()>>> question={}",question);
		return questionDAO.update(question);
	}
	@Override
	public int getTotalCount() throws Exception {
		logger.debug("QuestionServiceImpl.getTotalCount()>>> ");
		return questionDAO.getTotalNum();
	}

}

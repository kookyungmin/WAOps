package com.kookyungmin.waops.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Question;
import com.kookyungmin.waops.persistence.QuestionDAO;

@Service
public class QuestionServiceImpl implements QuestionService{
	private static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Inject
	QuestionDAO questionDAO;
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
	@Override
	public Question read(int qno) throws Exception {
		logger.debug("QuestionServiceImpl.read()>>> qno={}",qno);
		return questionDAO.read(qno);
	}
	@Override
	public int delete(int qno) throws Exception {
		logger.debug("QuestionServiceImpl.delete()>>> qno={}",qno);
		return questionDAO.delete(qno);
	}
	@Override
	public int update(Question question) throws Exception {
		logger.debug("QuestionServiceImpl.update()>>> question={}",question);
		return questionDAO.update(question);
	}

}

package com.kookyungmin.waops.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.FileNames;
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
	
	@Transactional
	@Override
	public int register(Question question) throws Exception {
		logger.debug("QuestionServiceImpl.register()>>> Question={}",question);
		int res = questionDAO.register(question);
		String[] fileNames = question.getFileNames();
		if(fileNames != null) {
			for(String fileName : fileNames) {
				questionDAO.addAttach(fileName);
			}	
		}
		
		return res;
	}
	
	//대부분의 데이터베이스가 기본으로 사용하는 수준으로, 다른 연결이 커밋하지 않은 데이터는 볼 수 없도록 함
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public Question read(int qno) throws Exception {
		logger.debug("QuestionServiceImpl.read()>>> qno={}",qno);
		questionDAO.updateViewCnt(qno);
		return questionDAO.read(qno);
	}
	@Transactional
	@Override
	public int delete(int qno) throws Exception {
		logger.debug("QuestionServiceImpl.delete()>>> qno={}",qno);
		replyDAO.deleteAll(qno);
		questionDAO.deleteAllAttach(qno);
		return questionDAO.delete(qno);
	}
	
	@Transactional
	@Override
	public int update(Question question) throws Exception {
		logger.debug("QuestionServiceImpl.update()>>> question={}, deleteFileNames={}", question);
		String[] fileNames = question.getFileNames();
		int qno = question.getQno();
		
		if(fileNames != null) {
			for(String fileName : fileNames) {
				questionDAO.appendAttach(fileName, qno);
			}	
		}
		fileNames = question.getDeleteFileNames();
		logger.debug("QuestionServiceImpl.update()>>>> deleteFileNames={}", fileNames);
		if(fileNames != null) {
			for(String fileName : fileNames) {
				questionDAO.deleteAttach(fileName);
			}
		}
		return questionDAO.update(question);
	}
	@Override
	public int getTotalCount() throws Exception {
		logger.debug("QuestionServiceImpl.getTotalCount()>>> ");
		return questionDAO.getTotalNum();
	}

	@Override
	public List<String> getAttach(Integer qno) throws Exception {
		return questionDAO.getAttach(qno);
	}

}

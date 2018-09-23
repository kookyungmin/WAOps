package com.kookyungmin.waops.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Question;

@Repository
public class QuestionDAOImpl implements QuestionDAO{
	private static Logger logger = LoggerFactory.getLogger(QuestionDAOImpl.class);
	
	private static final String NS = "QuestionMapper";
	private static final String LISTPAGE = NS + ".listPage";
	private static final String GETTOTALNUM = NS + ".getTotalNum";
	private static final String REGISTER = NS + ".register";
	private static final String READ = NS + ".read";
	
	@Inject
	SqlSession session;
	
	@Override
	public List<Question> listPage(Criteria cri) throws Exception {
		logger.debug("QuestionServiceImpl.listPage()>>> Criteria={}",cri);
		return session.selectList(LISTPAGE, cri);
	}

	@Override
	public int getTotalNum() throws Exception {
		logger.debug("QuestionServiceImpl.getTotalNum()>>>>");
		return session.selectOne(GETTOTALNUM);
	}

	@Override
	public int register(Question question) throws Exception {
		logger.debug("QuestionServiceImpl.register()>>> question={}",question);
		return session.insert(REGISTER, question);
	}

	@Override
	public Question read(int qno) throws Exception {
		logger.debug("QuestionServiceImpl.read()>>> qno={}", qno);
		return session.selectOne(READ, qno);
	}

}

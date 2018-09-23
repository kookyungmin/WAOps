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
	private static final String DELETE = NS + ".delete";
	private static final String UPDATE = NS + ".update";
	
	@Inject
	SqlSession session;
	
	@Override
	public List<Question> listPage(Criteria cri) throws Exception {
		logger.debug("QuestionDAOImpl.listPage()>>> Criteria={}",cri);
		return session.selectList(LISTPAGE, cri);
	}

	@Override
	public int getTotalNum() throws Exception {
		logger.debug("QuestionDAOImpl.getTotalNum()>>>>");
		return session.selectOne(GETTOTALNUM);
	}

	@Override
	public int register(Question question) throws Exception {
		logger.debug("QuestionDAOImpl.register()>>> question={}",question);
		return session.insert(REGISTER, question);
	}

	@Override
	public Question read(int qno) throws Exception {
		logger.debug("QuestionDAOImpl.read()>>> qno={}", qno);
		return session.selectOne(READ, qno);
	}

	@Override
	public int delete(int qno) throws Exception {
		logger.debug("QuestionDAOImpl.delete()>>> qno={}", qno);
		return session.delete(DELETE, qno);
	}

	@Override
	public int update(Question question) throws Exception {
		logger.debug("QusetionDAOImpl.update()>>> qusetion={}", question);
		return session.update(UPDATE, question);
	}

}

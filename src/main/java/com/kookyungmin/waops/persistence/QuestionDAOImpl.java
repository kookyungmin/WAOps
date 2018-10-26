package com.kookyungmin.waops.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static final String UPDATEREPLYCNT = NS + ".updateReplyCnt";
	private static final String UPDATEVIEWCNT = NS + ".updateViewCnt";
	private static final String APPENDATTACH = NS + ".appendAttach";
	private static final String ADDATTACH = NS + ".addAttach";
	private static final String GETATTACH = NS + ".getAttach";
	private static final String DELETEALLATTACH = NS + ".deleteAllAttach";
	private static final String DELETEATTACH = NS + ".deleteAttach";
	
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

	@Override
	public int updateReplyCnt(Integer qno, int amount) throws Exception {
		logger.debug("QuestionDAOImpl.updateReplyCnt() >>> qno= {}, amount={}" , qno, amount);
		Map<String, Object> map = new HashMap<>();
		map.put("qno", qno);
		map.put("amount", amount);
		return session.update(UPDATEREPLYCNT, map);
	}

	@Override
	public void updateViewCnt(int qno) throws Exception {
		session.update(UPDATEVIEWCNT, qno);
		
	}

	@Override
	public void appendAttach(String fileName, Integer qno) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("qno", qno);
		session.insert(APPENDATTACH, map);
		
	}

	@Override
	public void addAttach(String fileName) throws Exception {
		logger.debug("QuestionDAOImpl.addAttach>>> {}", fileName);
		session.insert(ADDATTACH, fileName);
		
	}

	@Override
	public List<String> getAttach(Integer qno) throws Exception {
		logger.debug("QuestionDAOImpl.getAttach>>>{}", qno);
		return session.selectList(GETATTACH, qno);
	}

	@Override
	public void deleteAllAttach(int qno) throws Exception {
		session.delete(DELETEALLATTACH, qno);
		
	}

	@Override
	public void deleteAttach(String fileName) throws Exception {
		session.delete(DELETEATTACH, fileName);
		
	}

}

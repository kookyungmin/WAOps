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
import com.kookyungmin.waops.domain.Reply;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	private static Logger logger = LoggerFactory.getLogger(ReplyDAOImpl.class);
	
	private static final String NS = "ReplyMapper";
	private static final String LISTPAGE = NS + ".listPage";
	private static final String GETTOTALNUM = NS + ".getTotalNum";
	private static final String REGISTER = NS + ".register";
	private static final String READ = NS + ".read";
	private static final String DELETE = NS + ".delete";
	private static final String UPDATE = NS + ".update";
	private static final String GETQNO = NS + ".getQno";
	private static final String DELETEALL = NS + ".deleteAll";

	
	@Inject
	private SqlSession session;
	
	@Override
	public int getTotalCount(int qno) throws Exception {
		logger.debug("ReplyDAOImpl.getTotalCount()>>> qno={}", qno);
		return session.selectOne(GETTOTALNUM, qno);
	}

	@Override
	public List<Reply> listPage(int qno, Criteria cri) throws Exception {
		logger.debug("ReplyDAOImpl.listPage()>>> qno={}, Criteria={}", qno, cri);
		Map<String, Object> map = new HashMap<>();
		map.put("qno", qno);
		map.put("cri", cri);
		return session.selectList(LISTPAGE, map);
	}

	@Override
	public Reply read(int rno) throws Exception {
		logger.debug("ReplyDAOImpl.read()>>> rno={}", rno);
		return session.selectOne(READ, rno);
	}

	@Override
	public int update(Reply reply) throws Exception {
		logger.debug("ReplyDAOImpl.update()>>> reply={}", reply);
		return session.update(UPDATE, reply);
	}

	@Override
	public int delete(int rno) throws Exception {
		logger.debug("ReplyDAOImpl.delete()>>> rno={}", rno);
		return session.delete(DELETE, rno);
	}

	@Override
	public int register(Reply reply) throws Exception {
		logger.debug("ReplyDAOImpl.register()>>> reply={}", reply);
		return session.insert(REGISTER, reply);
	}

	@Override
	public int getQno(int rno) throws Exception {
		logger.debug("ReplyDAOImpl.getQno()>>> rno={}", rno);
		return session.selectOne(GETQNO, rno);
	}

	@Override
	public void deleteAll(int qno) throws Exception {
		logger.debug("ReplyDAOImpl.deleteAll() >>> qno={}", qno);
		session.delete(DELETEALL,qno);
		
	}

}

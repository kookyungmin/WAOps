package com.kookyungmin.waops.service;

import java.util.List;
import java.util.Map;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Reply;

public interface ReplyService {
	int getTotalCount(int qno) throws Exception;
	List<Reply> listPage(int qno, Criteria cri) throws Exception;
	Reply read(int rno) throws Exception;
	Map<String, Object> register(Reply reply) throws Exception;
	int update(Reply reply) throws Exception;
	int delete(int rno) throws Exception;
}

package com.kookyungmin.waops.persistence;

import java.util.List;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Reply;

public interface ReplyDAO {
	int getTotalCount(int qno) throws Exception;
	List<Reply> listPage(int qno, Criteria cri) throws Exception;
	Reply read(int rno) throws Exception;
	int update(Reply reply) throws Exception;
	int delete(int rno) throws Exception;
	int register(Reply reply) throws Exception;
}

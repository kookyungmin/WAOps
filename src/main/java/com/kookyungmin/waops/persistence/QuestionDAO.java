package com.kookyungmin.waops.persistence;

import java.util.List;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Question;

public interface QuestionDAO{
	List<Question> listPage(Criteria cri) throws Exception;
	int getTotalNum() throws Exception;
	int register(Question question) throws Exception;
	Question read(int qno) throws Exception;
	int delete(int qno) throws Exception;
	int update(Question question) throws Exception;
	int updateReplyCnt(Integer qno, int amount) throws Exception;
	void updateViewCnt(int qno) throws Exception;
	void addAttach(String fileName) throws Exception;
	void appendAttach(String fileName, Integer qno) throws Exception;
	List<String> getAttach(Integer qno)throws Exception;
	void deleteAllAttach(int qno) throws Exception;
	void deleteAttach(String fileName) throws Exception;
}

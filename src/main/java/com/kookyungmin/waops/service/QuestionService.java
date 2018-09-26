package com.kookyungmin.waops.service;

import java.util.List;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Question;

public interface QuestionService {
	List<Question> listPage(Criteria cri) throws Exception;
	int register(Question question) throws Exception;
	Question read(int qno) throws Exception;
	int delete(int qno) throws Exception;
	int update(Question question) throws Exception;
	int getTotalCount() throws Exception;
}

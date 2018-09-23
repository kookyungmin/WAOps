package com.kookyungmin.waops.persistence;

import java.util.List;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.Question;

public interface QuestionDAO{
	List<Question> listPage(Criteria cri) throws Exception;
	int getTotalNum() throws Exception;
	int register(Question question) throws Exception;
	Question read(int qno) throws Exception;
}

package com.kookyungmin.waops.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.PageMaker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")

public class PageMakerTest {
	private static Logger logger = LoggerFactory.getLogger(PageMakerTest.class);
	
	
	@Test
	public void testConnection() throws Exception{
		Criteria cri = new Criteria(11, 10);
		int totalCount = 122;
		PageMaker pm = new PageMaker(cri, totalCount);
		logger.debug(pm.toString());
	}
}

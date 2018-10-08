package com.kookyngmin.waops.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kookyungmin.waops.controller.QuestionController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*.xml")


public class QuestionControllerTest {
	private static Logger logger = LoggerFactory.getLogger(QuestionControllerTest.class);
	
	//웹 응용 프로그램의 구성을 제공하는 인터페이스
	@Inject
	private WebApplicationContext wac;
	
	//톰캣을 작동시키지 않아도 컨트롤러 테스트를 진행하게 해줌
	private MockMvc mockmvc;
	
	@Before
	public void setup() {
		//스프링이 준 WebApplicationContext를 이용해서 mockmvc를 생성
		this.mockmvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.debug("setup QuestionControllerTest>>>");
	}
	
	@Test
	public void testListPage() throws Exception{
		logger.debug("QuestionControllerTest.testListPage()>>>>>>>>>>");
		this.mockmvc.perform(get("/questions/all/1/10"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(handler().handlerType(QuestionController.class))
					.andExpect(handler().methodName("listPage"));
	}
	
	
}

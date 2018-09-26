package com.kookyungmin.waops.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kookyungmin.waops.domain.Criteria;
import com.kookyungmin.waops.domain.PageMaker;
import com.kookyungmin.waops.domain.Question;
import com.kookyungmin.waops.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	private static Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Inject
	private QuestionService service;
	
	@RequestMapping(value = "/all/{page}/{perPageNum}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("page") int page,
			                                            @PathVariable("perPageNum") int perPageNum){
		logger.debug("QuestionController.listPage()>>>> page={}, perPageNum={}",page,perPageNum);
		try {
			Criteria cri = new Criteria(page, perPageNum);
			
			int totalCount = service.getTotalCount();
			List<Question> list = service.listPage(cri);
			PageMaker pm = new PageMaker(cri, totalCount);
			
			Map<String, Object> map = new HashMap<>();
			map.put("pageMaker", pm);
			map.put("list",list);
			
			return new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<Integer> register(@RequestBody Question question){
		logger.debug("QuestionController.register()>>>> question={}", question);
		try {
			int res = service.register(question);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{qno}", method = RequestMethod.GET)
	public ResponseEntity<Question> read(@PathVariable int qno){
		logger.debug("QuestionController.read()>>>> qno={}", qno);
		try {
			Question question = service.read(qno);
			return new ResponseEntity<>(question, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{qno}", method = RequestMethod.PUT)
	public ResponseEntity<Integer> update(@RequestBody Question question, @PathVariable int qno){
		logger.debug("QuestionController.update()>>>> question={}, qno={}", question, qno);
		try {
			question.setQno(qno);
			int res = service.update(question);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{qno}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@PathVariable int qno){
		logger.debug("QuestionController.read()>>>> qno={}", qno);
		try {
			int res = service.delete(qno);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

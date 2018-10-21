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
import com.kookyungmin.waops.domain.Reply;
import com.kookyungmin.waops.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
private static Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Inject
	private ReplyService service;
	
	@RequestMapping(value = "/qno/{qno}/all/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("qno") int qno,
														@PathVariable("page") int page){
		logger.debug("ReplyController.listPage()>>>> qno={} page={},", qno, page);
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			int totalCount = service.getTotalCount(qno);
			List<Reply> list = service.listPage(qno, cri);
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
	public ResponseEntity<Map<String, Object>> register(@RequestBody Reply reply){
		logger.debug("ReplyController.register()>>>> reply={}", reply);
		try {
			Map<String, Object> map = service.register(reply);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{rno}", method = RequestMethod.GET)
	public ResponseEntity<Reply> read(@PathVariable int rno){
		logger.debug("ReplyController.read()>>>> rno={}", rno);
		try {
			Reply reply = service.read(rno);
			return new ResponseEntity<>(reply, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{rno}", method = RequestMethod.PUT)
	public ResponseEntity<Integer> update(@PathVariable("rno") int rno,
									      @RequestBody Reply reply){
		logger.debug("ReplyController.update()>>>> reply={}, rno={}", reply, rno);
		try {
			reply.setRno(rno);
			int res = service.update(reply);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value="/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> delete(@PathVariable("rno") int rno){
		logger.debug("ReplyController.delete()>>>> rno={}", rno);
		try {
			int res = service.delete(rno);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

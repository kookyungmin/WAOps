package com.kookyungmin.waops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sixfinger")
public class SixFingerController {
	private static Logger logger = LoggerFactory.getLogger(SixFingerController.class);
	
	@RequestMapping(value = "/light", method = RequestMethod.POST)
	public ResponseEntity<String> lightSwitch(@RequestBody String state){
		logger.debug("SixFingerController.lightSwitch()>>>> state={}", state);
		try {
			return new ResponseEntity<>(state, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}
}

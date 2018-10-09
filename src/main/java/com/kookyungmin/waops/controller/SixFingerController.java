package com.kookyungmin.waops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sixfinger")
public class SixFingerController {
	private static Logger logger = LoggerFactory.getLogger(SixFingerController.class);
	private static String sendMessage = "on";
	
	@RequestMapping(value = "/light/{state}", method = RequestMethod.GET)
	public ResponseEntity<String> lightSwitch(@PathVariable("state") String state){
		logger.debug("SixFingerController.lightSwitch()>>>> state={}", state);
		try {
			sendMessage = state;
			return new ResponseEntity<>(state, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	@RequestMapping(value = "/sendArduino", method = RequestMethod.GET)
	public ResponseEntity<String> sendArduino(){
		try {
			return new ResponseEntity<>(sendMessage, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

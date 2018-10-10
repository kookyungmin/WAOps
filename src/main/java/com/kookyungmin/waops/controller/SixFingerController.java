package com.kookyungmin.waops.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sixfinger")
public class SixFingerController {
	private static Logger logger = LoggerFactory.getLogger(SixFingerController.class);
	private static Map<String, String> lightState = new HashMap<>();
	
	@RequestMapping(value = "/light/{state}", method = RequestMethod.GET)
	public ResponseEntity<String> lightSwitch(@PathVariable("state") String state,
											  @RequestParam("id") String id){
		logger.debug("SixFingerController.lightSwitch()>>>> state={}, id={}", state, id);
		try {
			if(!lightState.containsKey(id)) {
				lightState.put(id, state);
			}else {
				lightState.replace(id, state);
			}
			return new ResponseEntity<>(lightState.get(id), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value = "/sendArduino", method = RequestMethod.GET)
	public ResponseEntity<String> sendArduino(@RequestParam("id") String id){
		try {
			if(!lightState.containsKey(id)) {
				lightState.put(id, "on");
			}
			return new ResponseEntity<>(lightState.get(id), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/receiveArduino", method = RequestMethod.POST)
	public ResponseEntity<String> receiveArduino(){
		try {
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

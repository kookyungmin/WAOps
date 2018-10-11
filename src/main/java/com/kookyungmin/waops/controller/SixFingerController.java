package com.kookyungmin.waops.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kookyungmin.waops.domain.Temperature;


@RestController
@RequestMapping("/sixfinger")
public class SixFingerController {
	private static Logger logger = LoggerFactory.getLogger(SixFingerController.class);
	private static Map<String, String> lightState = new HashMap<>();
	private static Map<String, Object> temp = new HashMap<>();
	
	@RequestMapping(value = "/light/{state}", method = RequestMethod.GET)
	public ResponseEntity<String> lightSwitch(@PathVariable("state") String state,
											  @RequestParam("id") String id){
		logger.debug("SixFingerController.lightSwitch()>>>> state={}, id={}", state, id);
		try {
			if(!lightState.containsKey(id)) {
				Exception e = new Exception("기기의 와이파이 연결이 안되었습니다.");
				throw e;
			}
			lightState.replace(id, state);
			return new ResponseEntity<>(lightState.get(id), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	@RequestMapping(value = "/temperature", method = RequestMethod.GET)
	public ResponseEntity<Object> sendTemperature(@RequestParam("id") String id){
		logger.debug("SixFingerController.sendTemperature()>>> id = {}", id);
		try {
			if(!temp.containsKey(id)) {
				Exception e = new Exception("기기의 와이파이 연결이 안되었습니다.");
				throw e;
			}
			return new ResponseEntity<>(temp.get(id), HttpStatus.OK);
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
	public ResponseEntity<String> receiveArduino(@RequestParam("id") String id,
												 @RequestBody Temperature temperature){
		logger.debug("temp: {} , hum: {}", temperature.getTemperature(), temperature.getHumidity());
		try {
			if(!temp.containsKey(id)) {
				temp.put(id, temperature);
			}else {
				temp.replace(id, temperature);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}

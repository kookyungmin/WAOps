package com.kookyungmin.waops.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class SixthFingerHandler extends TextWebSocketHandler{
	
	private Logger logger = LoggerFactory.getLogger(SixthFingerHandler.class);
	private Map<String, WebSocketSession> sessions = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//연결됐을 때
		logger.debug("afterConnectionEstablished: {}" ,session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.debug("handleTextMessage>>> session={}, message={}" ,session , message);
		//protocol : cmd, sender, id, message
		//switch,android,sixthfinger1,"
		String msg = message.getPayload();
		if(StringUtils.isNotEmpty(msg)) {
			String[] strs = msg.split(",");
			if(strs != null && strs.length == 4) {
				String cmd = strs[0];
				String sender = strs[1];
				String id = strs[2];
				String mg = strs[3];
				if(cmd.equals("connected")) {
					logger.info("connected>>> senderid={}, message={}", sender + id, mg);
					sessions.put(sender + id , session);
				}else if(cmd.equals("switch")) {
					logger.info("switch>>>>>>status={}", mg);
					//아두이노에게 스위치 신호 보냄
					WebSocketSession receiveSession = sessions.get("arduino" + id);
					if(receiveSession != null) {
						receiveSession.sendMessage(new TextMessage(mg));
					}
				}else if(cmd.equals("temperature")) {
					if(sender.equals("android")) {
						logger.info("android request temperature to {}", "arduino" + id);
						WebSocketSession receiveSession = sessions.get("arduino" + id);
						if(receiveSession != null) {
							receiveSession.sendMessage(new TextMessage(mg));
						}
					}else {
						logger.info("arduino response temperature to {}","android" + id);
						WebSocketSession receiveSession = sessions.get("android" + id);
						if(receiveSession != null) {
							String[] temp = mg.split("/");
							logger.info("temperature={}, humidity={}", temp[0], temp[1]);
							receiveSession.sendMessage(new TextMessage(mg));
						}
					}
				}
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.debug("afterConnectionClosed>>> session={}, status={}",session, status);
	}
}

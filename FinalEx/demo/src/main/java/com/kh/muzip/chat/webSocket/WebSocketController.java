package com.kh.muzip.chat.webSocket;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.kh.muzip.chat.service.ChatService;
import com.kh.muzip.chat.vo.Alarm;
import com.kh.muzip.chat.vo.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSocketController {
    
    @Autowired
    public ChatService service;
	 
    
	@MessageMapping("/chat") // 클라이언트에서 "/chat"로 메시지를 보내면 이 핸들러가 호출
	@SendTo("/chat/chatget") // 이 핸들러가 반환하는 메시지는 "/chat/chatget" 주제로 브로드캐스트
	public ChatMessage handleChatMessage(@Payload ChatMessage message) {
        // 받은 메시지를 저장 로직
		message.setCreateDate(new Date(System.currentTimeMillis()));
		log.info("받아온 메세지는 어디로 갔을까?=={}",message);
		int result = service.insertMsg(message);
        
		return message;
    }
	
	@MessageMapping("/alarm")
	@SendTo("/alarm/alarmget")
	public Alarm handleAlarm(@Payload Alarm alarm) {
		
		// 알람 로직 예시
		// 채팅치면 /chat으로 보내고 처리된 결과가 /chatget으로 리액트로 보내짐
		// 리액트에서 메시지 수신성공했을 경우, 새로 요청보내서 alarm으로 요청
		// 이때 alarmKind에 따라서 아래와 같은 식으로 조건문 작성
		
		
		if(alarm.getAlarmKind().equals("chatting")) {
			alarm.setAlarmMessage("새로운 채팅 메시지가 있습니다.");
		}else if(alarm.getAlarmKind().equals("follow")) {
			alarm.setAlarmMessage(alarm.getSenderNo()+"님이 당신을 팔로우하였습니다.");
		}else if(alarm.getAlarmKind().equals("reply")) {
			
		}
		
		alarm.setCreateDate(new Date(System.currentTimeMillis()));
		
        // 받은 알람 저장
		// 처음 켰을 때 알람목록불러오는건 Controller에 작성?
//		int result = service.insertAlarm(alarm);
//		if(result > 0) {
//			return alarm;
//		}else {
//			return null;
//		}
		return null;
        
    }
}

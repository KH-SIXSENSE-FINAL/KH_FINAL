package com.kh.muzip.chat.webSocket;

import java.sql.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kh.muzip.chat.service.AlarmService;
import com.kh.muzip.chat.service.ChatService;
import com.kh.muzip.chat.vo.Alarm;
import com.kh.muzip.chat.vo.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSocketController extends TextWebSocketHandler{
    
    @Autowired
    public ChatService service;
    
    @Autowired
    private AlarmService alarmService;
    
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
		
		String alarmNo = "";
		
		String userId = alarmService.getUserId(alarm.getSenderNo());
		
		if(alarm.getAlarmKind().equals("chat")) {
			alarm.setAlarmMessage("새로운 채팅 메시지가 있습니다.");
			alarmNo = alarmService.insertChatAlarm(alarm);
		}else if(alarm.getAlarmKind().equals("follow")) {
			alarm.setAlarmMessage(userId+"님이 당신을 팔로우하였습니다.");
			alarmNo = alarmService.insertFollowAlarm(alarm);
		}else if(alarm.getAlarmKind().equals("reply")) {
			alarm.setAlarmMessage(userId+"님이 게시글에 댓글을 달았습니다.");
			alarmNo = alarmService.insertReplyAlarm(alarm);
		}
		
		alarm.setAlarmNo(alarmNo+"");
		
		return alarm;
        
    }
	
	
}

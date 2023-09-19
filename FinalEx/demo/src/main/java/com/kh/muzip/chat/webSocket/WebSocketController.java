package com.kh.muzip.chat.webSocket;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
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
    
    @MessageMapping("/chat")
    @SendTo("/chat/chatget")
    public ChatMessage handleChatMessage(@Payload ChatMessage message) {
        // 받은 메시지를 저장 로직
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        message.setCreateDate(currentTimestamp);
        log.info("받아온 메세지는 어디로 갔을까?=={}{}", message, message.getCreateDate());
        int result = service.insertMsg(message);

        return message;
    }

	
	@MessageMapping("/alarm")
	@SendTo("/alarm/alarmget")
	public Alarm handleAlarm(@Payload Alarm alarm) {
		
		String alarmNo = "";
		
		if(alarm.getAlarmKind().equals("chat")) {
			
			alarm.setAlarmMessage("새로운 채팅 메시지가 있습니다.");
			alarmNo = alarmService.insertChatAlarm(alarm);
			
		}else if(alarm.getAlarmKind().equals("follow")) {
			
			String userId = alarm.getSenderNo();
			String senderUserNo = alarmService.searchUserNo(userId);
			String receiverUserNo = alarmService.searchUserNo(alarm.getReceiverNo());
			
			alarm.setSenderNo(senderUserNo);
			alarm.setReceiverNo(receiverUserNo);
			alarm.setAlarmMessage(userId+"님이 당신을 팔로우하였습니다.");
			
			alarmNo = alarmService.insertFollowAlarm(alarm);
		}else if(alarm.getAlarmKind().equals("reply")) {
			
			String userId = alarmService.getUserId(alarm.getSenderNo());
			String receiverNo = alarmService.getReplyReceiverNo(alarm.getAlarmPath());
			alarm.setReceiverNo(receiverNo);
			alarm.setAlarmMessage(userId+"님이 게시글에 댓글을 달았습니다.");
			alarmNo = alarmService.insertReplyAlarm(alarm);
			
		}
		
		alarm.setAlarmNo(alarmNo+"");
		
		return alarm;
        
    }
	
	
}

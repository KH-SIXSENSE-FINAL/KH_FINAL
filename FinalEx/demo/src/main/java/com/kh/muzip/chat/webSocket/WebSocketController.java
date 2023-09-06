package com.kh.muzip.chat.webSocket;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.kh.muzip.chat.service.ChatService;
import com.kh.muzip.chat.vo.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebSocketController {
    
    @Autowired
    public ChatService service;
	 
    
	@MessageMapping("/chat")
	@SendTo("/chat/chatget")
	public ChatMessage handleChatMessage(@Payload ChatMessage message) {
        // 받은 메시지를 저장 로직
		message.setCreateDate(new Date(System.currentTimeMillis()));
		log.info("받아온 메세지는 어디로 갔을까?=={}",message);
		int result = service.insertMsg(message);
        
		return message;
    }
}

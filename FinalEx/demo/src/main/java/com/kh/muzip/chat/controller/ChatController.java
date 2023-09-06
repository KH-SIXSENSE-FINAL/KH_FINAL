package com.kh.muzip.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.chat.service.ChatService;
import com.kh.muzip.chat.vo.ChatMessage;
import com.kh.muzip.chat.vo.ChatMessageRepository;
import com.kh.muzip.chat.vo.ChatRoom;
import com.kh.muzip.chat.vo.ChatRoomJoin;
import com.kh.muzip.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping("/messages")
public class ChatController {
	    
    @Autowired
    public ChatService service;

    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/checkChatroom")
    public ResponseEntity<Integer> createChatRoom(@RequestParam("userId") String userId,@RequestParam("memberId") String memberId, ChatRoom room,ChatRoomJoin join){
    	
    	Map<String, Object> params = new HashMap<>();
	    params.put("userId", userId); 
	    params.put("memberId",memberId);
    	
    	room.setUserId(userId);
    	room.setChatroomName(memberId);
    	int chatRoomNo = service.createChatRoom(room,params);
    	log.info("채팅방 번호 == {}",chatRoomNo);
		
		if(chatRoomNo > 1) { 
			join.setUserId(memberId);
			join.setChatroomNo(chatRoomNo);
			int joinUs = service.joinRoom(join);
		}
    	return ResponseEntity.ok(chatRoomNo);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessage>> messageRepo(@RequestParam("chatroomNo") int chatroomNo){
    	List<ChatMessage> messages = service.messageRepo(chatroomNo);
    	
    	return ResponseEntity.ok(messages);
    }
    
}

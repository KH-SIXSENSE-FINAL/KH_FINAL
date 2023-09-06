package com.kh.muzip.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.chat.dao.ChatDao;
import com.kh.muzip.chat.vo.ChatMessage;
import com.kh.muzip.chat.vo.ChatRoom;
import com.kh.muzip.chat.vo.ChatRoomJoin;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatDao chatDao;
	
	@Override
	public int createChatRoom(ChatRoom room,Map<String, Object> params) {
		return chatDao.createChatRoom(room,params);
	}
	
	@Override
	public int joinRoom(ChatRoomJoin join) {
		return chatDao.joinRoom(join);
	}
	
	@Override
	public int insertMsg(ChatMessage message) {
		return chatDao.insertMsg(message);
	}
	
	@Override
	public List<ChatMessage> messageRepo(int chatroomNo){
		return chatDao.messageRepo(chatroomNo);
	}
}

package com.kh.muzip.chat.service;

import java.util.List;
import java.util.Map;

import com.kh.muzip.chat.vo.ChatMessage;
import com.kh.muzip.chat.vo.ChatRoom;
import com.kh.muzip.chat.vo.ChatRoomJoin;


public interface ChatService {
//	private final ChatMessageRepository chatMR;
	
//	@Autowired
//	private ChatDao chatDao;
	
//	private final List<ChatMessage> messages = new ArrayList<>();
//
//    public List<ChatMessage> getAllMessages() {
//        return messages;
//    }
//
//    public void addMessage(ChatMessage message) {
//        messages.add(message);
//    }
    int createChatRoom(ChatRoom room,Map<String, Object> params);
    int joinRoom(ChatRoomJoin join);
	int insertMsg(ChatMessage message);
	List<ChatMessage> messageRepo(int chatroomNo);
	List<ChatRoom> searchChatlist(Map<String, Object> params);
	List<ChatRoom> Chatlist(String userId);
    
}

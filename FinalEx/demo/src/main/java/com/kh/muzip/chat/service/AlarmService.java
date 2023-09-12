package com.kh.muzip.chat.service;

import java.util.ArrayList;
import java.util.List;

import com.kh.muzip.chat.vo.Alarm;

public interface AlarmService {

	List<Integer> getChatRoomNo(String userNo);
	List<Alarm> getAlarms(String userNo, List<Integer> chatRoomNo);
	String getUserId(String userNo);
	String insertChatAlarm(Alarm alarm);
	String insertFollowAlarm(Alarm alarm);
	String insertReplyAlarm(Alarm alarm);
	void checkAlarm(String alarmNo);
	String getReplyReceiverNo(String boardNo);
	String searchUserNo(String userId);
}

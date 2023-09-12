package com.kh.muzip.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.chat.dao.AlarmDao;
import com.kh.muzip.chat.vo.Alarm;

@Service
public class AlarmServiceImpl implements AlarmService{
	
	@Autowired
	private AlarmDao alarmDao;
	
	@Override
	public List<Integer> getChatRoomNo(String userNo){
		return alarmDao.getChatRoomNo(userNo);
	}

	@Override
	public List<Alarm> getAlarms(String userNo, List<Integer> chatRoomNo) {
		return alarmDao.getAlarms(userNo, chatRoomNo);
	}
	
	@Override
	public String getUserId(String userNo) {
		return alarmDao.getUserId(userNo);
	}

	@Override
	public String insertChatAlarm(Alarm alarm) {
		return alarmDao.insertChatAlarm(alarm);
	}

	@Override
	public String insertFollowAlarm(Alarm alarm) {
		return alarmDao.insertFollowAlarm(alarm);
	}

	@Override
	public String insertReplyAlarm(Alarm alarm) {
		return alarmDao.insertReplyAlarm(alarm);
	}

	@Override
	public void checkAlarm(String alarmNo) {
		alarmDao.checkAlarm(alarmNo);
	}
	
	@Override
	public String getReplyReceiverNo(String boardNo) {
		return alarmDao.getReplyReceiverNo(boardNo);
	}

	@Override
	public String searchUserNo(String userId) {
		return alarmDao.searchUserNo(userId);
	}

}

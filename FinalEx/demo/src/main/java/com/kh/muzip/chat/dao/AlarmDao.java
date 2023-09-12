package com.kh.muzip.chat.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.muzip.chat.vo.Alarm;

@Repository
public class AlarmDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public List<Integer> getChatRoomNo(String userNo){
		return session.selectList("alarmMapper.getChatRoomNo", userNo);
	}
	
	public List<Alarm> getAlarms(String userNo, List<Integer> chatRoomNo){
	      ArrayList<Alarm> returnList = new ArrayList<Alarm>();
	      List<Alarm> list1 = new ArrayList<Alarm>();
	      if(chatRoomNo.size() != 0) {         
	         list1 = session.selectList("alarmMapper.getChatAlarms", chatRoomNo);
	      }
	      
	      returnList.addAll(list1);
	      
	      List<Alarm> list2 = session.selectList("alarmMapper.getAlarms", userNo) ;
	      
	      returnList.addAll(list2);
	      
	      return returnList;
	   }
	
	public String getUserId(String userNo) {
		return session.selectOne("alarmMapper.getUserId", userNo);
	}
	
	public String insertChatAlarm(Alarm alarm) {
		session.insert("alarmMapper.insertChatAlarm", alarm);
		return alarm.getAlarmNo();
	}
	public String insertFollowAlarm(Alarm alarm) {
		session.insert("alarmMapper.insertFollowAlarm", alarm);
		return alarm.getAlarmNo();
	}
	public String insertReplyAlarm(Alarm alarm) {
		// boardNo 받아오는 과정 추가
		session.insert("alarmMapper.insertReplyAlarm", alarm);
		return alarm.getAlarmNo();
	}
	
	public void checkAlarm(String alarmNo) {
		session.update("alarmMapper.checkAlarm", alarmNo);
	}

}

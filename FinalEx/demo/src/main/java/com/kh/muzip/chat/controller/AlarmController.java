package com.kh.muzip.chat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.chat.service.AlarmService;
import com.kh.muzip.chat.vo.Alarm;

@RestController
public class AlarmController {
	
	@Autowired
	private AlarmService alarmService;
	
	@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAlarms")
    public List<Alarm> getAlarms(@RequestParam("userNo") String userNo){
    	
		List<Integer> chatRoomNo = alarmService.getChatRoomNo(userNo);
		
		List<Alarm> list = alarmService.getAlarms(userNo, chatRoomNo);
    	
    	return list;
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/checkAlarm")
	public void checkAlarm(@RequestParam("alarmNo") String alarmNo){
		alarmService.checkAlarm(alarmNo);
	}

}

package com.kh.muzip.setting.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.setting.model.dao.SettingDao;
import com.kh.muzip.setting.model.vo.Genre;
import com.kh.muzip.setting.model.vo.setting;

@Service
public class SettingServiceImpl implements SettingService{
	
	
	@Autowired
	private SettingDao settingDao;
	
	@Override
	public List<Genre> getGenre(int userNo) {
		return settingDao.getGenre(userNo);
	}

	@Override
	public int setGenre(int userNo , List<String> list) {
		
		// 1. 현재 사용자의 모든 장르정보 삭제 deleteGenre
		settingDao.deleteGenre(userNo);
    	// 2. 사용자가 체크한 장르정보들 추가. insertGenre
		int result = settingDao.insertGenre(userNo, list); 
		
		return result;
	}

	@Override
	public int setMemberinfo(int userNo, String userName, String email) {
		int result = settingDao.setMemberinfo(userNo, userName, email);
		
		return result;
	}

	@Override
	public int setpassword(int userNo,  String changePwd) {
		int result = settingDao.setpassword(userNo,changePwd);
		return result;
	}

	@Override
	public setting getSetting(int userNo) {
		return settingDao.getSetting(userNo);
		
	}

	@Override
	public int changeSetting(int userNo, String cName, String result) {
		
		int result2 = 0;
		
		
		result2 = settingDao.changeSetting(userNo,cName, result);
		
		return result2;
		
	}
	
}

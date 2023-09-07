package com.kh.muzip.setting.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.muzip.setting.model.vo.Genre;
import com.kh.muzip.setting.model.vo.setting;

@Repository
public class SettingDao {

	@Autowired
	private SqlSessionTemplate session;
	
	public List<Genre> getGenre(int userNo) {
		return session.selectList("setting.getGenre" , userNo);
	}

	public void deleteGenre(int userNo) {
		session.delete("setting.deleteGenre" , userNo);
	}

	public int insertGenre(int userNo, List<String> list) {
		int result = 1;
		for(String genreName : list) {
			HashMap<String , Object> map = new HashMap();
			map.put("userNo", userNo);
			map.put("genreName",genreName);
			result *= session.insert("setting.insertGenre" , map);
		}
		return result;
	}

	public int setMemberinfo(int userNo, String userName, String email) {
		int result = 1;
		HashMap<String, Object> map = new HashMap();
		map.put("userNo", userNo);
		map.put("userName", userName);
		map.put("email", email);
		result = session.update("setting.setMemberinfo",map);
		return result;
	}

	public int setpassword(int userNo, String changePwd) {
		int result = 1;
		HashMap<String, Object> map = new HashMap();
		map.put("userNo", userNo);
		map.put("changePwd", changePwd);
		result = session.update("setting.setpassword",map);
		return result;
	}

	public setting getSetting(int userNo) {
		
		return session.selectOne("setting.getSetting",userNo);
		
		
	}

	public int changeSetting(int userNo, String cName, String result) {
		
		
		HashMap<String, Object> map = new HashMap();
		map.put("userNo", userNo);
		map.put("result", result);
		
		switch(cName) {
		case("autoPlay"): return session.update("setting.setautoPlay",map); 
		case("theme"): return session.update("setting.settheme",map); 
		case("chatAlarm"): return session.update("setting.setchatAlarm",map); 
		case("musicAlarm"): return session.update("setting.setmusicAlarm",map); 
		case("commentAlarm"): return session.update("setting.setcommentAlarm",map);
		default: return 0;
		}
		
		
	}

	public int withdrawal(int userNo) {
		
		return session.update("setting.withdrawal", userNo);
	}

	
}

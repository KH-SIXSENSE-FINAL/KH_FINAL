package com.kh.muzip.setting.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.setting.model.vo.Contact;
import com.kh.muzip.setting.model.vo.Genre;
import com.kh.muzip.setting.model.vo.setting;


public interface SettingService {

	
	
	List<Genre> getGenre(int userNo);
	int setGenre(int userNo ,  List<String> list);
	int setMemberinfo(int userNo, String userName, String email);
	int setpassword(int userNo, String changePwd);
	setting getSetting(int userNo);
	int changeSetting(int userNo, String cName, String result);
	int withdrawal(int userNo);
	int selectContactListCount(String category, String researchinput);
	ArrayList<Contact> selectContactList(PageInfo pi, String category, String researchinput);
	int insertContact(HashMap<String, Object> map);
}

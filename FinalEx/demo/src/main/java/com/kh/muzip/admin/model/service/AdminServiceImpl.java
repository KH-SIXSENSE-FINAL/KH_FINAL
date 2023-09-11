package com.kh.muzip.admin.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.admin.model.dao.AdminDao;
import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.member.model.vo.Member;
import com.kh.muzip.setting.model.dao.SettingDao;
import com.kh.muzip.setting.model.vo.Genre;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public int selectMemberListCount() {
		return adminDao.selectMemberListCount();
	}

	@Override
	public ArrayList<Member> selectMemberList(PageInfo pi) {
		return adminDao.selectMemberList(pi);
	}

	@Override
	public int updateMemberinfo(Member member) {
		return adminDao.updateMemberinfo(member);
	}

	@Override
	public int WithdrawalMemberinfo(Member member) {
		return adminDao.WithdrawalMemberinfo(member);
	}

	@Override
	public int RestoreMemberinfo(Member member) {
		return adminDao.RestoreMemberinfo(member);
	}
	
	
}

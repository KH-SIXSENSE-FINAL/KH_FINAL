package com.kh.muzip.admin.model.service;

import java.util.ArrayList;

import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.member.model.vo.Member;

public interface AdminService {

	int selectMemberListCount();

	ArrayList<Member> selectMemberList(PageInfo pi);

	int updateMemberinfo(Member member);

	int WithdrawalMemberinfo(Member member);

	int RestoreMemberinfo(Member member);

}

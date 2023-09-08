package com.kh.muzip.admin.model.service;

import java.util.ArrayList;

import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.member.model.vo.Member;

public interface AdminService {

	int selectListCount();

	ArrayList<Member> selectMemberList(PageInfo pi);

}

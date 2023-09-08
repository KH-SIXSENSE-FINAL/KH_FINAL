package com.kh.muzip.admin.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.member.model.vo.Member;
import com.kh.muzip.setting.model.vo.Genre;

@Repository
public class AdminDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	

	public int selectListCount() {
		
		return session.selectOne("admin.selectListCount");
	}
	
	



	public ArrayList<Member> selectMemberList(PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * 10;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)session.selectList("admin.selectMemberList", null, rowBounds);
	} 

}

package com.kh.muzip.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.muzip.member.model.vo.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSessionTemplate session;
	
	public int insertMember(Member member) {
		return session.insert("member.insertMember",member);
	}
	
	public Member loginMember(String userId) {
		return session.selectOne("member.loginMember",userId);
	}
	
	public List<Member> searchMembers(String query){
		return session.selectList("member.searchMembers",query);
	}
	
	public boolean checkFollow(String userId, String memberId) {
		Map<String, String> paramMap = new HashMap<>();
	    paramMap.put("userId", userId);
	    paramMap.put("memberId", memberId);
	    
		int result =  session.selectOne("follow.checkFollow",paramMap);
		if(result >0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public int addFollow(String userId, String memberId) {
		Map<String, String> paramMap = new HashMap<>();
	    paramMap.put("userId", userId);
	    paramMap.put("memberId", memberId);
	    
		return session.update("follow.addFollow",paramMap);
	}
	
	public int unFollow(String userId, String memberId) {
		Map<String, String> paramMap = new HashMap<>();
	    paramMap.put("userId", userId);
	    paramMap.put("memberId", memberId);
	    
		return session.update("follow.unFollow",paramMap);
	}
}

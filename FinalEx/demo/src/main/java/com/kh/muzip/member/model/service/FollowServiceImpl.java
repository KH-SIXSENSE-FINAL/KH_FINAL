package com.kh.muzip.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.member.model.dao.FollowDao;
import com.kh.muzip.member.model.vo.Follow;

@Service
public class FollowServiceImpl implements FollowService{

	 @Autowired
	    private FollowDao followDAO;

	   
		@Override
		public List<Follow> getFollowsByUserId(String userId) {
			return followDAO.getFollowsByUserId(userId);
		}


		@Override
		public List<Follow> getFollowerList(String userId) {
			return followDAO.getFollowerList(userId);
		}


		


		@Override
		public Object insertFollowStatus(Follow follow) {
			return followDAO.insertFollowStatus(follow);
			
		}
}

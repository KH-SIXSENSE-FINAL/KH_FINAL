package com.kh.muzip.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.member.model.dao.FollowDao;
import com.kh.muzip.member.model.vo.Follow;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
    private FollowDao followDao;

   
    public List<Follow> getFollowsByUserId(String userId) {
        return followDao.getFollowsByUserId(userId);
    }
}

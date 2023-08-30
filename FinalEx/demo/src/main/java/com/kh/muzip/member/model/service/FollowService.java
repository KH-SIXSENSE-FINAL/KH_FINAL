package com.kh.muzip.member.model.service;

import java.util.List;

import com.kh.muzip.member.model.vo.Follow;

public interface FollowService {
    List<Follow> getFollowsByUserId(String userId);
}

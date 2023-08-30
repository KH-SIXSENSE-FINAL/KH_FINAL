package com.kh.muzip.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.member.model.service.FollowService;
import com.kh.muzip.member.model.vo.Follow;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    @Autowired
    private FollowService followService;


    @GetMapping
    public List<Follow> getFollowsForUser(@RequestParam("userId") String userId) {
        // userId를 기반으로 해당 유저의 팔로우 데이터 가져오기
        return followService.getFollowsByUserId(userId);
    }
}

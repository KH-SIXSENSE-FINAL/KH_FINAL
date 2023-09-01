package com.kh.muzip.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.member.model.service.FollowService;
import com.kh.muzip.member.model.vo.Follow;

@RestController


public class FollowController {

    @Autowired
    private FollowService followService;
    
    @CrossOrigin(origins = "http://localhost:3000")
        @GetMapping("/followlist")
        public List<Follow> getFollowsForUser(@RequestParam("userId") String userId) {
            // userId를 기반으로 해당 유저의 팔로우 데이터 가져오기
            return followService.getFollowsByUserId(userId);
        }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/followerlist")
    public List<Follow> getFollowerList(@RequestParam String userId) {
        return followService.getFollowerList(userId);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/clickFollow") // 클라이언트에서 보낸 POST 요청을 처리하는 엔드포인트
    public ResponseEntity<String> clickFollow(@RequestBody Follow follow) {
        try {
            // 클라이언트에서 받은 userId와 memberId를 기반으로 새로운 팔로우 관계 추가
            followService.insertFollowStatus(follow);

            return ResponseEntity.ok("팔로우 추가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("팔로우 추가 실패");
        }
    }

    
    
    
}


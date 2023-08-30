package com.kh.muzip.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.muzip.member.model.vo.Follow;

@Repository
public class FollowDao {
    @Autowired
    private SqlSessionTemplate session;

    public List<Follow> getFollowsByUserId(String userId) {
        // 실제 데이터베이스 쿼리를 수행하고 결과를 반환하는 로직을 작성
        return session.selectList("follow.getFollowsByUserId", userId);
    }
}

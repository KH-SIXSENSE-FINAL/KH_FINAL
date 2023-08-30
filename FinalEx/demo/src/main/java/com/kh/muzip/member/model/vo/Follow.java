package com.kh.muzip.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {

	private String userId;//팔로워 아이디
	private String memberId;//팔로잉한 아이디
	private String status;//현재 팔로잉 상태
}

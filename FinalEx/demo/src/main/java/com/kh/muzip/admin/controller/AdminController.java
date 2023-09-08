package com.kh.muzip.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.admin.model.vo.Pagination;
import com.kh.muzip.admin.model.service.AdminService;
import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.member.model.vo.Member;
import com.kh.muzip.setting.controller.SettingController;
import com.kh.muzip.setting.model.vo.Genre;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectListCount")
	public ResponseEntity<?> selectListCount(@RequestBody HashMap<String, Object> m){
		
		int listCount = adminService.selectListCount();
		
		return ResponseEntity.ok().body(listCount);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectMemberList")
	public ResponseEntity<?> selectMemberList(@RequestBody HashMap<String, Object> m) {

		// --------------------페이징 처리------------------------

		int listCount = adminService.selectListCount();
		int currentPage = m.get("currentPage") != null ? (int) m.get("currentPage") : 1;
		int pageLimit = 10; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int boardLimit = 10; // 한 페이지에 보여질 게시글의 최대 갯수

		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		ArrayList<Member> list = adminService.selectMemberList(pi);

//		HashMap<String, Object> map = new HashMap();
//		map.put("pi", pi);
//		map.put("list", list);

//		return ResponseEntity.ok().body(map);
		return ResponseEntity.ok().body(list);
	}

}

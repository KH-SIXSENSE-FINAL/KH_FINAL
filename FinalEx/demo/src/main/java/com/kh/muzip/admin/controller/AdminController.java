package com.kh.muzip.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.admin.model.vo.Pagination;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.admin.model.service.AdminService;
import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.member.model.vo.Member;
import com.kh.muzip.music.model.vo.Music;
import com.kh.muzip.setting.controller.SettingController;
import com.kh.muzip.setting.model.vo.Genre;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AdminController {

	@Autowired
	private AdminService adminService;

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectMemberListCount")
	public ResponseEntity<?> selectMemberListCount(@RequestBody HashMap<String, Object> m) {

		int listCount = adminService.selectMemberListCount();

		return ResponseEntity.ok().body(listCount);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectMemberList")
	public ResponseEntity<?> selectMemberList(@RequestBody HashMap<String, Object> m) {
	    
	    int listCount = adminService.selectMemberListCount();
	    int currentPage = m.get("currentPage") != null ? (int) m.get("currentPage") : 1;
	    String sortBy = m.get("sortBy") != null ? (String) m.get("sortBy") : "default";
	    String searchQuery = m.get("searchQuery") != null ? (String) m.get("searchQuery") : null; // 검색어 추가

	    int pageLimit = 10; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
	    int boardLimit = 10; // 한 페이지에 보여질 게시글의 최대 갯수

	    PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);

	    ArrayList<Member> list;

	    if(searchQuery != null && !searchQuery.trim().isEmpty()) {
	        // 검색어가 있을 경우, 검색어 기반으로 회원 목록 가져오기
	        list = adminService.selectMemberListBySearchQuery(searchQuery, sortBy);
	    } else {
	        // 검색어가 없을 경우, 기존 로직 사용 , 여기엔 정렬바꾸는거 포함
	        list = adminService.selectMemberList(pi, sortBy);
	    }

	    return ResponseEntity.ok().body(list);
	}



	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/updateMemberinfo")
	public ResponseEntity<?> updateMemberinfo(@RequestBody Member member) {

		int result = adminService.updateMemberinfo(member);

		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "정보가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "정보 수정에 실패했습니다."));
		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/WithdrawalMemberinfo")
	public ResponseEntity<?> WithdrawalMemberinfo(@RequestBody Member member) {

		int result = adminService.WithdrawalMemberinfo(member);

		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "정보가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "정보 수정에 실패했습니다."));
		}

	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/RestoreMemberinfo")
	public ResponseEntity<?> RestoreMemberinfo(@RequestBody Member member) {
		
		int result = adminService.RestoreMemberinfo(member);
		
		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "정보가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "정보 수정에 실패했습니다."));
		}
		
	}
	
	
	//-----------------------------회원관리--------------------------------------
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectContentListCount")
	public ResponseEntity<?> selectContentListCount(@RequestBody HashMap<String, Object> m) {

		int listCount = adminService.selectContentListCount();

		return ResponseEntity.ok().body(listCount);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectContentList")
	public ResponseEntity<?> selectContentList(@RequestBody HashMap<String, Object> m) {


		int listCount = adminService.selectContentListCount();
		int currentPage = m.get("currentPage") != null ? (int) m.get("currentPage") : 1;
		int pageLimit = 10; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int boardLimit = 10; // 한 페이지에 보여질 게시글의 최대 갯수

		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		ArrayList<Board> list = adminService.selectContentList(pi);


		return ResponseEntity.ok().body(list);
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/adminDeleteContent")
	public ResponseEntity<?> adminDeleteContent(@RequestBody Board board) {

		int result = adminService.adminDeleteContent(board);

		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "정보가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "정보 수정에 실패했습니다."));
		}

	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/adminRestoreContent")
	public ResponseEntity<?> adminRestoreContent(@RequestBody Board board) {
		
		int result = adminService.adminRestoreContent(board);
		
		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "정보가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "정보 수정에 실패했습니다."));
		}
		
	}
	
	
	//-------------------------------------글 관리-------------------------------------------------
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectMusicListCount")
	public ResponseEntity<?> selectMusicListCount(@RequestBody HashMap<String, Object> m) {

		int listCount = adminService.selectMusicListCount();

		return ResponseEntity.ok().body(listCount);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectMusicList")
	public ResponseEntity<?> selectMusicList(@RequestBody HashMap<String, Object> m) {


		int listCount = adminService.selectMusicListCount();
		int currentPage = m.get("currentPage") != null ? (int) m.get("currentPage") : 1;
		int pageLimit = 10; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int boardLimit = 10; // 한 페이지에 보여질 게시글의 최대 갯수

		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		ArrayList<Music> list = adminService.selectMusicList(pi);


		return ResponseEntity.ok().body(list);
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/adminDeleteMusic")
	public ResponseEntity<?> adminDeleteMusic(@RequestBody Music music) {

		int result = adminService.adminDeleteMusic(music);

		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "정보가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "정보 수정에 실패했습니다."));
		}

	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/adminRestoreMusic")
	public ResponseEntity<?> adminRestoreMusic(@RequestBody Music music) {
		
		int result = adminService.adminRestoreMusic(music);
		
		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "정보가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "정보 수정에 실패했습니다."));
		}
		
	}
	
	
	
	

}

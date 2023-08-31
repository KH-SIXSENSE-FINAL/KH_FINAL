package com.kh.muzip.board.controller;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.board.model.service.BoardService;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ServletContext application;
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/insertBoard")
	public String insertBoard(
			@RequestBody Map<String,Object> insertItems) {
		Map<String,Object> board = (Map<String, Object>) insertItems.get("board");
		//Map<String,Object> aList = (Map<String, Object>) insertItems.get("aList");
		
		Board b = new Board();
		b.setUserNo(board.get("userNo")+"");
		b.setBoardContent((String) board.get("boardContent"));
		b.setSecret((String) board.get("secret"));
		
		log.info("아이템 {}",b);
		//log.info("이미지 {}",aList);
		

		
		return "연결됨";
	}
	
	
	
}

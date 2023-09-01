package com.kh.muzip.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.muzip.board.model.service.BoardService;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.board.model.vo.BoardExt;
import com.kh.muzip.member.model.vo.Member;
import com.kh.muzip.board.model.vo.Attachment;
import com.kh.muzip.common.Utils;

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
	public ResponseEntity<String> insertBoard(
				@RequestParam(value="files" , required=false) List<MultipartFile> files,
			    @RequestParam("boardContent") String boardContent,
			    @RequestParam("userNo") String userNo,
			    @RequestParam("secret") String secret) {
		
		// 피드 사진파일 레벨 10번
		int fileLevel = 10;
		
		String webPath = "/resources/image/";
		String serverFolderPath = application.getRealPath(webPath);	
		
		Board b = new Board();
		b.setBoardContent(boardContent);
		b.setUserNo(userNo);
		b.setSecret(secret);
		
		File dir = new File(serverFolderPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		List<Attachment> atList = new ArrayList();
		if(files != null) {
		for(MultipartFile upfile : files) {
			String changeName = Utils.saveFile(upfile, serverFolderPath);
			Attachment at = new Attachment();
			at.setChangeName(changeName);
			at.setOriginName(upfile.getOriginalFilename());
			at.setFileLevel(fileLevel);
			at.setUserNo(userNo);
			atList.add(at);
			log.info("파일 {}",at);
			}
		}
		
		int result = 0;
		
		try {
			result = boardService.insertBoard(b,atList,serverFolderPath,webPath);
		} catch (Exception e) {
			log.error("error = {}" , e.getMessage());
		}
		
		if(result > 0 ) {
			return ResponseEntity.ok("게시물 등록 성공하였습니다.");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시물 등록 실패하였습니다."); 
		}
		
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/selectBoardList")
	public ResponseEntity<List<BoardExt>> selectBoardList(){
		
		
	List<BoardExt> boardList = boardService.selectBoardList();
		
		log.info("보드 {}",boardList.get(0));
	if(boardList.isEmpty() ) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(boardList); 
	}else {
		return ResponseEntity.ok(boardList);
	}
}
	
}

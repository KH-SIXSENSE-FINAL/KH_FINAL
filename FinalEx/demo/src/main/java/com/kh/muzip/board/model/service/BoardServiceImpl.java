package com.kh.muzip.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.board.model.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService{
		
	@Autowired
	private BoardDao boardDao;
	
	
	
		
}
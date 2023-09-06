package com.kh.muzip.board.model.service;

import java.util.List;

import com.kh.muzip.board.model.vo.Attachment;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.board.model.vo.BoardExt;

public interface BoardService {

	public int insertBoard(Board b, List<Attachment> atList, String serverFolderPath, String webPath) throws Exception;

	public List<BoardExt> selectBoardList();
	
	
	
}

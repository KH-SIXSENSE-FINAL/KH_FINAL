package com.kh.muzip.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.muzip.board.model.vo.Attachment;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.board.model.vo.BoardExt;
import com.kh.muzip.board.model.vo.Reply;

public interface BoardService {

	public int insertBoard(Board b, List<Attachment> atList, String serverFolderPath, String webPath) throws Exception;

	public List<BoardExt> selectBoardList();

	public String boomUp(Map<String, String> boomUpData);

	public Reply insertReply(Reply r);

	public List<Map<String, String>> getUserIdList();

	public List<Map<String, String>> getUserProfileImgList();

	public List<Map<String, String>> getAllMusicList();
	
}

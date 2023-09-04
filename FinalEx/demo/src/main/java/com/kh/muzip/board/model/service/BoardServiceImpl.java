package com.kh.muzip.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.muzip.board.model.dao.BoardDao;
import com.kh.muzip.board.model.vo.Attachment;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.board.model.vo.BoardExt;
import com.kh.muzip.common.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
		

	@Autowired
	private BoardDao boardDao;
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int insertBoard(Board b, List<Attachment> atList, String serverFolderPath, String webPath) throws Exception {

		
		b.setBoardContent(Utils.XSSHandling(b.getBoardContent()));
		b.setBoardContent(Utils.newLineHandling(b.getBoardContent()));
		
		int boardNo = boardDao.insertBoard(b);
		log.info("보드넘버",boardNo);
		int result = 0;
		if(boardNo > 0 && !atList.isEmpty()) {
			for(Attachment at  : atList) {
				at.setBoardNo(boardNo+"");
				at.setFilePath(webPath);
				log.info("아이템 {}"+at);
			}
			result = boardDao.insertAttachmentList(atList);
			if(result != atList.size()) { // 이미지 삽입 실패시 강제 예외발생
				throw new Exception("예외발생");
			}
		}else {
			result = boardNo;
		}
		
		return result;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public List<BoardExt> selectBoardList() {
		List<BoardExt> boardList = boardDao.selectBoardList();
		
		for(BoardExt b : boardList) {
			b.setBoardContent(Utils.newLineClear(b.getBoardContent()));
		}
		
		
		return boardList;
	}
}

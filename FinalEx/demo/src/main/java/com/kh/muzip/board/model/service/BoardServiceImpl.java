package com.kh.muzip.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.muzip.board.model.dao.BoardDao;
import com.kh.muzip.board.model.vo.Attachment;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.board.model.vo.BoardExt;
import com.kh.muzip.board.model.vo.Reply;
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
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public List<Map<String, String>> getUserIdList() {
		List<Map<String, String>> idList = boardDao.getUserIdList();
		return idList;
	}
	
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public List<Map<String, String>> getUserProfileImgList() {
		List<Map<String, String>> imgList = boardDao.getUserProfileImgList();
		return imgList;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public List<Map<String, String>> getAllMusicList() {
		List<Map<String, String>> allMusicList = boardDao.getAllMusicList();
		return allMusicList;
	}
	
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public String boomUp(Map<String, String> boomUpData) {
		Object selectResult = null;
		selectResult = boardDao.selectBoomUp(boomUpData);
		
		String resultValue = "";
		int result = 0;
		if(selectResult != null) {
			result = boardDao.deleteBoomUp(boomUpData);
			if(result > 0) {
				resultValue = "삭제";
			}
		}else {
			result = boardDao.insertBoomUp(boomUpData);
			if(result > 0) {
				resultValue = "삽입";
			}
		}
		return resultValue;
	}
	
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public Reply insertReply(Reply r) {
		int replyNo = 0;
		Reply returnR = new Reply();
		replyNo = boardDao.insertReply(r);
		if(replyNo > 0 ) {
			returnR = boardDao.selectReplyOne(replyNo);
		}
		return returnR;
	}
	
	
}

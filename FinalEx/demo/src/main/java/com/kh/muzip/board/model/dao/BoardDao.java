package com.kh.muzip.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.muzip.board.model.vo.Attachment;
import com.kh.muzip.board.model.vo.Board;

@Repository
public class BoardDao {
	
	
	@Autowired
	private SqlSession sqlSession;

	public int insertBoard(Board b) {
		int result = 0;
		result = sqlSession.insert("board.insertBoard",b);
												// b => 매개변수로 넘겨줘서 얕은복사 일어남
		if(result > 0) {
			result = Integer.parseInt(b.getBoardNo());
			// 게시글 삽입 성공시 selectKey태그를 사용하여 세팅한 boardNo값을 b에 담아줌.
		}
		
		return result;
	}

	public int insertAttachmentList(List<Attachment> atList) {
		return sqlSession.insert("board.insertAttachmentList",atList);
	}
	
}

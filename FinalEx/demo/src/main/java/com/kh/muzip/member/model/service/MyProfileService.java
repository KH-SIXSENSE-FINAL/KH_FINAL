package com.kh.muzip.member.model.service;

import com.kh.muzip.board.model.vo.Attachment;
import com.kh.muzip.member.model.vo.Member;

public interface MyProfileService {

	boolean saveProfileImg(Attachment pat);

	int saveMemberInfo(Member memberData);

	boolean saveBackImg(Attachment bat);

	void deactivateImage(String userNo, int fileLevel);

	

}

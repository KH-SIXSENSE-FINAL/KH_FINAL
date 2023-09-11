package com.kh.muzip.admin.model.service;

import java.util.ArrayList;

import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.member.model.vo.Member;
import com.kh.muzip.music.model.vo.Music;

public interface AdminService {

	int selectMemberListCount();

	ArrayList<Member> selectMemberList(PageInfo pi);

	int updateMemberinfo(Member member);

	int WithdrawalMemberinfo(Member member);

	int RestoreMemberinfo(Member member);

	int selectContentListCount();

	ArrayList<Board> selectContentList(PageInfo pi);

	int adminDeleteContent(Board board);

	int adminRestoreContent(Board board);

	int selectMusicListCount();

	ArrayList<Music> selectMusicList(PageInfo pi);

	int adminDeleteMusic(Music music);

	int adminRestoreMusic(Music music);

}
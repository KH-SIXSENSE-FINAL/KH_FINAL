package com.kh.muzip.admin.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.admin.model.dao.AdminDao;
import com.kh.muzip.admin.model.vo.PageInfo;
import com.kh.muzip.board.model.vo.Board;
import com.kh.muzip.member.model.vo.Member;
import com.kh.muzip.music.model.vo.Music;
import com.kh.muzip.setting.model.dao.SettingDao;
import com.kh.muzip.setting.model.vo.Genre;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public int selectMemberListCount() {
		return adminDao.selectMemberListCount();
	}

	@Override
	public ArrayList<Member> selectMemberList(PageInfo pi) {
		return adminDao.selectMemberList(pi);
	}

	@Override
	public int updateMemberinfo(Member member) {
		return adminDao.updateMemberinfo(member);
	}

	@Override
	public int WithdrawalMemberinfo(Member member) {
		return adminDao.WithdrawalMemberinfo(member);
	}

	@Override
	public int RestoreMemberinfo(Member member) {
		return adminDao.RestoreMemberinfo(member);
	}

	@Override
	public int selectContentListCount() {
		return adminDao.selectContentListCount();
	}

	@Override
	public ArrayList<Board> selectContentList(PageInfo pi) {
		return adminDao.selectContentList(pi);
	}

	@Override
	public int adminDeleteContent(Board board) {
		return adminDao.adminDeleteContent(board);
	}

	@Override
	public int adminRestoreContent(Board board) {
		return adminDao.adminRestoreContent(board);
	}

	@Override
	public int selectMusicListCount() {
		return adminDao.selectMusicListCount();
	}

	@Override
	public ArrayList<Music> selectMusicList(PageInfo pi) {
		return adminDao.selectMusicList(pi);
	}

	@Override
	public int adminDeleteMusic(Music music) {
		return adminDao.adminDeleteMusic(music);
	}

	@Override
	public int adminRestoreMusic(Music music) {
		return adminDao.adminRestoreMusic(music);
	}
	
	
}

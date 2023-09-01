package com.kh.muzip.music.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.muzip.music.model.dao.MusicDao;
import com.kh.muzip.music.model.vo.Music;
import com.kh.muzip.music.model.vo.Playlist;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MusicServiceImpl implements MusicService{
	
	@Autowired
	private MusicDao musicDao;

	@Override
	public ArrayList<Playlist> selectPlaylist(int userNo) {
		return musicDao.selectPlaylist(userNo);
	}

	@Override
	public ArrayList<ArrayList<Music>> selectRecommendList(List genreList) {
		return musicDao.selectRecommendList(genreList);
	}

}

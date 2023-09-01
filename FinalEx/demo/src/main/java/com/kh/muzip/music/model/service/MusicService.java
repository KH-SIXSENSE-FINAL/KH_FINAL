package com.kh.muzip.music.model.service;

import java.util.ArrayList;
import java.util.List;

import com.kh.muzip.music.model.vo.Music;
import com.kh.muzip.music.model.vo.Playlist;

public interface MusicService {
	
	ArrayList<Playlist> selectPlaylist(int userNo);
	ArrayList<ArrayList<Music>> selectRecommendList(List genreList);

}

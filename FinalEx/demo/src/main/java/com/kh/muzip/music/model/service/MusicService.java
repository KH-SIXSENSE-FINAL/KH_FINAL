package com.kh.muzip.music.model.service;

import java.util.ArrayList;
import java.util.List;

import com.kh.muzip.music.model.vo.Music;
import com.kh.muzip.music.model.vo.MusicFile;
import com.kh.muzip.music.model.vo.Playlist;

public interface MusicService {
	
	ArrayList<Playlist> selectPlaylist(int userNo);
	ArrayList<ArrayList<Music>> selectRecommendList(List genreList);
	int insertMusic(Music m, MusicFile musicFile, 
					String imageServerFolderPath, String imgaeWebPath, String mp3ServerFolderPath, String mp3WebPath) throws Exception;

}

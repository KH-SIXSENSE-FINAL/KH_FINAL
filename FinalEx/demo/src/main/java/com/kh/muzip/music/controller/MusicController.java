package com.kh.muzip.music.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.music.model.service.MusicService;
import com.kh.muzip.music.model.vo.Music;
import com.kh.muzip.music.model.vo.Playlist;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MusicController {
	
	@Autowired
	private MusicService musicService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/myPlaylist")
	public ArrayList<Playlist> myPlaylist(
			@RequestParam("userNo") String userNo
			){
		
		ArrayList<Playlist> playlist = musicService.selectPlaylist(Integer.parseInt(userNo));
		
		return playlist;
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/recommendList")
	public ArrayList<ArrayList<Music>> recommendList(
			@RequestBody Map<String, List<String>> requestData
			){
		List<String> genreList = requestData.get("genre");
//		List<String> genreList = new ArrayList<String>();
//		for(int i = 0; i < genre.size(); i++) {			
//			genreList.add((String) genre.get(i));
//		}
		
		ArrayList<ArrayList<Music>> recommendList = musicService.selectRecommendList(genreList);
		log.info("recommendList == {}", recommendList);
		return recommendList;
		
	}

}

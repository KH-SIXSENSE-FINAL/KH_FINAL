package com.kh.muzip.music.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.muzip.board.model.vo.Attachment;
import com.kh.muzip.common.Utils;
import com.kh.muzip.music.model.service.MusicService;
import com.kh.muzip.music.model.vo.Music;
import com.kh.muzip.music.model.vo.MusicFile;
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
		
		ArrayList<ArrayList<Music>> recommendList = musicService.selectRecommendList(genreList);
		
		return recommendList;
	}
	
	@Autowired
	private ServletContext application;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value ="/insertMusic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public int insertMusic(
			@RequestParam("cover") MultipartFile cover,
			@RequestParam("music") MultipartFile music,
			@RequestParam("title") String title, 
			@RequestParam("artist") String artist,
			@RequestParam("lyrics") String lyrics,
			@RequestParam("genre") String genre
			){

		Music m = new Music();
		m.setMusicTitle(title);
		m.setMusicArtist(artist);
		m.setMusicLyrics(lyrics);
		m.setGenre(genre);
		
		String imgaeWebPath="/resources/image/";
		String mp3WebPath="/resources/mp3/";
		String imageServerFolderPath = application.getRealPath(imgaeWebPath);
		String mp3ServerFolderPath = application.getRealPath(mp3WebPath);
		
		// 디렉토리 생성, 해당 디렉토리가 존재하지 않는다면 생성
		File dir1 = new File(imageServerFolderPath);
		File dir2 = new File(mp3ServerFolderPath);
		if(!dir1.exists()) dir1.mkdirs();
		if(!dir2.exists()) dir2.mkdirs();
		
		MusicFile musicFile = new MusicFile();
		
		if(!cover.isEmpty()) {			
			// 파일명 재정의 + 저장
			String changeName = Utils.saveFile(cover, imageServerFolderPath);
			musicFile.setCoverChangeName(changeName);
			musicFile.setCoverOriginName(cover.getOriginalFilename());
		};
		if(!music.isEmpty()) {
			String changeName = Utils.saveFile(music, mp3ServerFolderPath);
			musicFile.setMusicChangeName(changeName);
			musicFile.setMusicOriginName(music.getOriginalFilename());
		}
			
		int result = 0;
		
		try {
			result = musicService.insertMusic(m, musicFile, 
					imageServerFolderPath, imgaeWebPath, mp3ServerFolderPath, mp3WebPath);
		} catch (Exception e) {
			log.error("error = {}", e.getMessage());
		}
		
		return result;		
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/insertPlaylist")
	public int insertPlaylist(
			@RequestParam("listName") String playlistName,
			@RequestParam("userNo") String userNo
			){		
		return musicService.insertPlaylist(playlistName, userNo);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/deletePlaylist")
	public int deletePlaylist(
			@RequestParam("playlistNo") String playlistNo
			){		
		return musicService.deletePlaylist(playlistNo);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/selectOneMusic")
	public Music selectOneMusic(
			@RequestParam("playlistNo") String musicNo
			){
		return musicService.selectOneMusic(musicNo);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/addPlaylistSong")
	public int addPlaylistSong(
			@RequestParam("playlistNo") String playlistNo,			
			@RequestParam("musicNo") String musicNo
			){
		return musicService.addPlaylistSong(playlistNo, musicNo);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/removePlaylistSong")
	public int removePlaylistSong(
			@RequestParam("playlistNo") String playlistNo,			
			@RequestParam("musicNo") String musicNo
			){
		return musicService.removePlaylistSong(playlistNo, musicNo);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/increaseCount")
	public int increaseCount(
			@RequestParam("musicNo") String musicNo,
			HttpServletRequest request, HttpServletResponse response
			){
		int result = 0;
        String uniqueCookieValue = musicNo;
        String cookieName = "playMusicNo";
        Cookie[] cookies = request.getCookies();

        // 이전에 설정된 쿠키가 없을 경우 새 쿠키 생성
        if (cookies == null || Arrays.stream(cookies).noneMatch(cookie -> cookie.getName().equals(cookieName))) {
        	Cookie Cookie = new Cookie(cookieName, uniqueCookieValue);
            Cookie.setMaxAge(3600); // 1시간 (초 단위)
            response.addCookie(Cookie);

            result = musicService.increaseCount(musicNo);
        }else {
        	String[] arr = null;
        	for(Cookie c : cookies) {
        		if(c.getName().equals(cookieName)) {        			
        			arr = c.getValue().split("/");
        			List<String> list = Arrays.asList(arr);
        			
        			// 기존 쿠키값에 현재 게시글 번호와 일치하는 값이 없는 경우
        			if(list.indexOf(musicNo+"") == -1) {
        				c.setValue(c.getValue()+"/"+musicNo);
        				response.addCookie(c);
        				result =musicService.increaseCount(musicNo);
        			}
        		}
        	}
        }

        return result;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/searchMusic")
	public List<Music> searchMusic(
			@RequestParam("keyword") String keyword
			){
		return musicService.searchMusic(keyword);
	}
	


}

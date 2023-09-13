package com.kh.muzip.setting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.muzip.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

import com.kh.muzip.member.model.vo.Member;
import com.kh.muzip.setting.model.service.SettingService;
import com.kh.muzip.setting.model.vo.Genre;
import com.kh.muzip.setting.model.vo.PaymentHistory;
import com.kh.muzip.setting.model.vo.setting;

@RestController
@Slf4j
public class SettingController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SettingService settingService;
	
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/getGenre")
	public ResponseEntity<?> getGenre(@RequestBody HashMap<String, Object> m) {

		List<Genre> list = settingService.getGenre((int) m.get("userNo"));

		return ResponseEntity.ok().body(list);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/getMemberinfo")
	public ResponseEntity<?> getMemberinfo(@RequestBody HashMap<String, Object> m) {
		Member loginUser = memberService.loginMember((String) m.get("userId"));

		return ResponseEntity.ok(loginUser);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/setMemberinfo")
	public ResponseEntity<?> setMemberinfo(@RequestBody HashMap<String, Object> m) {

		int result1 = settingService.setMemberinfo((int) m.get("userNo"), (String) m.get("userName"),
				(String) m.get("email"));

		int result2 = settingService.setGenre((int) m.get("userNo"), (List<String>) m.get("list"));

		if (result1 * result2 > 0) {
			return ResponseEntity.ok().body(Map.of("message", "정보가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "정보 수정에 실패했습니다."));
		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/setpassword")
	public ResponseEntity<?> setpassword(@RequestBody HashMap<String, Object> m) {

		int result = 0;

		Member loginUser = memberService.loginMember((String) m.get("userId"));

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String changPwd = encoder.encode((String)m.get("changePwd"));
		boolean passwordMatches = encoder.matches((String)m.get("memberPwd"),loginUser.getUserPwd());
//		boolean passwordMatches = encoder.matches((String)m.get("changePwd"),(String)m.get("memberPwd"));

		if (passwordMatches) {
			result = settingService.setpassword((int) m.get("userNo"), changPwd);
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "현재 비밀번호가 일치하지 않습니다."));
		}

		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "비밀번호가 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "비밀번호 변경에 실패했습니다."));
		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/getSetting")
	public ResponseEntity<?> getSetting(@RequestBody HashMap<String, Object> m) {

		setting membersetting = settingService.getSetting((int)m.get("userNo"));
		
		return ResponseEntity.ok(membersetting);

	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/changeSetting")
	public ResponseEntity<?> changeSetting(@RequestBody HashMap<String, Object> m){
		
		int userNo = (int)m.get("userNo");
		String cName = (String)m.get("cName");
		String result = (String)m.get("result"); // 변경할 값이 'Y' 인지 'N'인지
		int result2 = 0;
		
		result2 = settingService.changeSetting(userNo, cName,result);
		
		if (result2 > 0) {
			return ResponseEntity.ok().body(Map.of("message", "설정이 수정되었습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "설정 변경에 실패했습니다."));
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/withdrawal")
	public ResponseEntity<?> withdrawal(@RequestBody HashMap<String, Object> m){
		
		int userNo = (int)m.get("userNo");
		String userId = (String)m.get("userId");
		String memerPwd = (String)m.get("memberPwd");
		
		int result = 0;
		
		
		
		Member loginUser = memberService.loginMember(userId);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean passwordMatches = encoder.matches(memerPwd,loginUser.getUserPwd());
		
		if (passwordMatches) {
			result = settingService.withdrawal(userNo);
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "현재 비밀번호가 일치하지 않습니다."));
		}
		
		if (result > 0) {
			return ResponseEntity.ok().body(Map.of("message", "회원 탈퇴 되셨습니다."));
		} else {
			return ResponseEntity.badRequest().body(Map.of("message", "회원 탈퇴에 실패했습니다."));
		}
		
	}
	
	
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/updateMembership")
	public ResponseEntity<String> updateMembership(@RequestBody PaymentHistory p ) {
		String msg = p.getMembershipNo() == 1 ? "구독 취소되었습니다." :
					 p.getMembershipNo() == 2 ? "리스너플랜이 구독되었습니다." : "라이터플랜이 구독되었습니다.";
		int result = settingService.updateMembership(p);

		if(result > 0) {
			return ResponseEntity.ok().body(msg);
		}else {
			return ResponseEntity.badRequest().body("실패하였습니다.");
			
		}
	}
	
	
	
	
	
}










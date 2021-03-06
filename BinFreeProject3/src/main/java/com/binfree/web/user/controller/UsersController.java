package com.binfree.web.user.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.binfree.web.buddy.service.BuddyService;
import com.binfree.web.user.domain.KakaoProfile;
import com.binfree.web.user.domain.OAuthToken;
import com.binfree.web.user.domain.UsersVO;
import com.binfree.web.user.security.CustomUserDetails;
import com.binfree.web.user.service.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/user/*")
public class UsersController {

	@Setter(onMethod_ = @Autowired)
	private BCryptPasswordEncoder pwencoder;

	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UsersService usersService;

	@Autowired
	private BuddyService buddyService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@GetMapping("/loginpage")
	public String loginInput(String success, String error, String logout, Model model) {

		log.info("error: " + error);
		log.info("logout: " + logout);

		if (error != null) {
			model.addAttribute("error", "???????????? ?????? ???????????? ???????????? ?????????. ?????? ??????????????????.");
		}

		if (logout != null) {
			model.addAttribute("logout", "???????????? ???????????????!");
		}

		if (success != null) {

		}

		return "user/login_page";

	}

	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}

	// ????????????
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(UsersVO user, Model model) throws Exception {

		logger.info("join ??????");

		if (usersService.getEmail(user) == null) {

			String password = user.getPassword();
			password = pwencoder.encode(password);
			user.setPassword(password);
			user.setEnabled(true);
			user.setAuth("ROLE_MEMBER");

			usersService.userJoin(user);

			logger.info("join service ??????");
			
			model.addAttribute("msg", "??????????????? ?????????????????????! ????????? ???????????? ????????????");
			return "user/login_page";
		} else {
			model.addAttribute("msg", "?????? ???????????? ??????????????????.");
			return "user/signup";
		}
	}

	// ?????? ??????
	@ResponseBody
	@RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
	// ????????? ????????? ?????? ?????? ?????? String?????? ??????
	public String emailCheck(
			@RequestBody String email/* email??? ??? ??????????????? ??? ??????????????? ??? ??????????????? @RequestParam Map<String, String> paramMap */)
			throws Exception {
		/* email ?????? */
		System.out.println(email);

		/*
		 * email ?????? ??? email??? null ??? ?????? paramMap?????? ???????????? ??????????????? String email =
		 * paramMap.get("email");
		 */

		int count = 0;
		count = usersService.emailCheck(email);
		System.out.println(count);
		// ????????? ??????
		if (count == 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}

	// ????????? ?????????
	@GetMapping(value = "/kakao/callback", produces = "application/json; charset=utf8")
	public String kakaoCallback(String code, HttpServletRequest request, Model model) throws Exception { // Data??? ???????????????
																											// ???????????? ??????

		// POST ???????????? key=value ???????????? ??????(??????????????????)
		RestTemplate rt = new RestTemplate();
		// HttpHeaders ???????????? ??????
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBody ???????????? ??????
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "858c3d8b6a1fe9814e96f3b2dbb03e09");
		params.add("redirect_uri ", "http://52.14.170.10:8080/BinFreeProject/user/kakao/callback");
		params.add("code", code);

		// HttpHeader ??? HttpBody ??? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http ???????????? -Post ???????????? -????????? response ????????? ?????? ??????
		ResponseEntity<String> response = rt.exchange("Https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("????????? ????????? ??????:" + oauthToken.getAccess_token());

		RestTemplate rt2 = new RestTemplate();
		// HttpHeaders ???????????? ??????
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader ??? HttpBody ??? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		System.out.println(kakaoProfileRequest2);

		// Http ???????????? -Post ???????????? -????????? response ????????? ?????? ??????
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest2, String.class);
		System.out.println(response2.getBody());

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// User ???????????? : username,password,email
		System.out.println("????????? ?????????(??????) : " + kakaoProfile.getId());
		System.out.println("????????? ????????? : " + kakaoProfile.getKakao_account().getEmail());

//				System.out.println("??????????????? ????????????: " + kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		System.out.println("??????????????? ?????????:" + kakaoProfile.getKakao_account().getEmail());

		String garbagePassword = UUID.randomUUID().toString();
		garbagePassword = pwencoder.encode(garbagePassword);
		System.out.println("??????????????? ????????????:" + garbagePassword);

		CustomUserDetails kakaoUser = CustomUserDetails.builder().email(kakaoProfile.getKakao_account().getEmail())
				.password(garbagePassword.toString()).build();

		// ????????? ?????? ???????????? ?????? ?????? ??????
//				System.out.println(kakaoUser.getEmail());

		kakaoUser.setName(kakaoProfile.getProperties().nickname);

		CustomUserDetails originUser = usersService.getKakaoUserInfo(kakaoUser.getEmail());


		if (originUser == null) {
			System.out.println("?????? ???????????????.");
			usersService.userJoinKakao(kakaoUser);
			originUser = usersService.getKakaoUserInfo(kakaoUser.getEmail());
		}

		// ????????? ????????? ?????? ??????
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				originUser.getEmail(), originUser.getPassword(), originUser.getAuthorities()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/";


	}

	@GetMapping("/mypage")
	public String goMypage(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails loginUserVO = (CustomUserDetails) authentication.getPrincipal();

		CustomUserDetails loginUserInfo = new CustomUserDetails();
		loginUserInfo = usersService.getLoginUserInfo(loginUserVO.getUsername());

		model.addAttribute("userInfo", loginUserInfo);
		model.addAttribute("myBuddyInfo", buddyService.get(loginUserInfo.getMyBuddy()));

		return "user/mypage";
	}

	@ResponseBody
	@PostMapping("/modify_userInfo")
	public void modifyUserInfo(@RequestBody Map<String, String> paramMap) {

		UsersVO modifyUserInfo = new UsersVO();

		modifyUserInfo.setEmail(paramMap.get("email"));
		modifyUserInfo.setPhone(paramMap.get("phone"));

		usersService.setModifyUserInfo(modifyUserInfo);

	}

	@ResponseBody
	@PostMapping("/modify_subInfo")
	public void modifySubInfo(@RequestBody Map<String, String> paramMap, HttpServletRequest request) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails loginUserVO = (CustomUserDetails) authentication.getPrincipal();
		CustomUserDetails loginUserInfo = new CustomUserDetails();

		loginUserInfo = usersService.getLoginUserInfo(loginUserVO.getUsername());

		UsersVO modifySubInfo = new UsersVO();

		modifySubInfo.setEmail(loginUserInfo.getEmail());
		modifySubInfo.setSubName(paramMap.get("subName"));
		modifySubInfo.setSubPhone(paramMap.get("subPhone"));
		modifySubInfo.setZipCode(paramMap.get("zipCode"));
		modifySubInfo.setLoc(paramMap.get("loc"));
		modifySubInfo.setInputLoc(paramMap.get("inputLoc"));

		System.out.println(modifySubInfo);

		usersService.setModifySubInfo(modifySubInfo);

	}

	@ResponseBody
	@PostMapping("/modify_pwd")
	public void modifyPwd(@RequestBody String getPwd, HttpServletRequest request) {

		log.info("getPwd : " + getPwd);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails loginUserVO = (CustomUserDetails) authentication.getPrincipal();
		CustomUserDetails loginUserInfo = new CustomUserDetails();

		loginUserInfo = usersService.getLoginUserInfo(loginUserVO.getUsername());

		UsersVO modifyPwd = new UsersVO();

		String password = pwencoder.encode(getPwd);
		modifyPwd.setEmail(loginUserInfo.getEmail());
		modifyPwd.setPassword(password);

		usersService.setModifyPwd(modifyPwd);

	}

	@ResponseBody
	@PostMapping("/bye_user")
	public void byeUser(@RequestBody String getEmail) {
		System.out.println(getEmail);
		usersService.byeUser(getEmail);
		SecurityContextHolder.clearContext();
	}

	@RequestMapping(value = "/findpw", method = RequestMethod.GET)
	public void findPwGET() throws Exception {

	}

	@RequestMapping(value = "/findpw", method = RequestMethod.POST)
	@ResponseBody
	public String findPwPOST(String name, String email) {
		int emailCnt = usersService.getUserEmailCnt(email);
		int nameCnt = usersService.getUserNameCnt(name);

		if (emailCnt == 0) {
			return "emailNull";

		} else if (nameCnt == 0) {
			return "nameNull";

		} else {
			usersService.findPw(name, email);

			return "success";
		}
	}

}

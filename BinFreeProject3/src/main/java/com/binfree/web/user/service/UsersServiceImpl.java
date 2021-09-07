package com.binfree.web.user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.binfree.web.user.domain.UsersVO;
import com.binfree.web.user.mapper.UsersMapper;
import com.binfree.web.user.security.CustomUserDetails;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

	@Autowired
	private BCryptPasswordEncoder pwencoder;

	@Autowired
	private UsersMapper usersMapper;

	@Override
	public void userJoin(UsersVO user) throws Exception {
		usersMapper.userJoin(user);
	}

	@Override
	public void insertUserAuth(UsersVO user) throws Exception {
		usersMapper.insertUserAuth(user);
	}

	@Override
	public UsersVO getEmail(UsersVO user) {
		return usersMapper.getEmail(user);
	}

	@Override
	public CustomUserDetails getLoginUserInfo(String email) {

		System.out.println("usersServiceImple 진입 : " + email);

		CustomUserDetails user = new CustomUserDetails();
		user = usersMapper.getLoginUserInfo(email);
		System.out.println("usersServiceImple에서 userMapper 호출  : " + user.getName());

		return user;
	}

	@Override
	public void setModifyUserInfo(UsersVO modifyUserInfo) {

		usersMapper.setModifyUserInfo(modifyUserInfo);
	}

	@Override
	public void setModifyPwd(UsersVO modifyPwd) {
		usersMapper.setModifyPwd(modifyPwd);
	}

	@Override
	public void setModifySubInfo(UsersVO modifySubInfo) {

		usersMapper.setModifySubInfo(modifySubInfo);
	}

	@Override
	public void byeUser(String email) {

		usersMapper.byeUser(email);
	}

	@Override
	public int emailCheck(String email) {
		return usersMapper.emailCheck(email);
	}

	@Override
	public void setSubInfo(UsersVO modifySubInfo) {

		usersMapper.setSubInfo(modifySubInfo);
	}

	@Override
	public int userCount() {
		return usersMapper.userCount();
	}

	@Override
	public UsersVO getUserInfo(String email) {
		return usersMapper.getUserInfo(email);
	}

	@Override
	public void setBuddy(UsersVO vo) {
		usersMapper.setBuddy(vo);
	}

	@Override
	public void sendEmail(UsersVO vo, String pw, String div) {
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "rudwls1378@gmail.com";
		String hostSMTPpwd = "govl0919@";

		String fromEmail = "bfree@gmail.com";
		String fromName = "BFree";
		String subject = "";
		String msg = "";

		if (div.equals("findpw")) {
			subject = "BFree 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getName() + "님의 임시 비밀번호 입니다. <br/>비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += pw + "</p><br/>";
			msg += "<a href='http://localhost:8080/user/loginpage'>로그인하러 가기</a><br/></div>";
//			msg += "<a href='http://52.14.170.10:8080/user/loginpage'>로그인하러 가기</a><br/></div>";
		}

		// 받는 사람 이메일
		String mail = vo.getEmail();

		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			log.info("메일 발송 실패: " + e);
		}

	}

	@Override
	public void findPw(String name, String email) {
		UsersVO user = usersMapper.getUserInfo(email);

		String pw = "";
		for (int i = 0; i < 12; i++) {
			pw += (char) ((Math.random() * 26) + 97);

		}

		String password = pwencoder.encode(pw);
		user.setPassword(password);
		
		usersMapper.setModifyPwd(user);
		sendEmail(user, pw, "findpw");

	}

	@Override
	public int getUserEmailCnt(String email) {
		return usersMapper.getUserEmailCnt(email);
	}

	@Override
	public int getUserNameCnt(String email) {
		return usersMapper.getUserNameCnt(email);
	}

	// 카카오 회원가입
	@Override
	public void userJoinKakao(CustomUserDetails kakaoUser) throws Exception {
		usersMapper.userJoinKakao(kakaoUser);
	}

	// 카카오 프로필 조회
	@Override
	public CustomUserDetails getKakaoUserInfo(String email) {

		return usersMapper.getKakaoUserInfo(email);

	}
}

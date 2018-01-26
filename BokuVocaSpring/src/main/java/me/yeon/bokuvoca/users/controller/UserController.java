package me.yeon.bokuvoca.users.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.yeon.bokuvoca.HomeController;
import me.yeon.bokuvoca.users.dao.UserDAO;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDAO dao;
	
	/**
	 * 로그인 페이지 진입
	 * @return
	 */
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String goLogin(){
		return "login";
	}
	
	/**
	 * 로그인 전송
	 * @return
	 */
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session){
		
		return "login";
	}
	
	/**
	 * 회원 가입 페이지
	 * @return
	 */
	@RequestMapping(value="join", method = RequestMethod.GET)
	public String goJoin(){
		return "join";
	}
	
	/**
	 * 회원 가입 완료
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="join", method = RequestMethod.POST)
	public String join(){
		return "";
	}
	
	/**
	 * 비동기 사용자 중복 확인
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="vaildateId", method = RequestMethod.POST)
	public String vaildateId(){
		return "";
	}
	
	/**
	 * 정보 페이지
	 * @return
	 */
	@RequestMapping(value="info", method = RequestMethod.GET)
	public String info(){
		return "info";
	}
	
	/**
	 * 프로파일 페이지
	 * @return
	 */
	@RequestMapping(value="profile", method = RequestMethod.GET)
	public String profile(Model model){
		//단순 카드형의 ajax 호출에 대응
		
		//그 외 접속
		
		return "profile";
	}
}

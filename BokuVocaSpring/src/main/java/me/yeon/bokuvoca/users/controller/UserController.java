package me.yeon.bokuvoca.users.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import me.yeon.bokuvoca.HomeController;
import me.yeon.bokuvoca.users.dao.UserDAO;
import me.yeon.bokuvoca.users.vo.JUser;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	String[] greets = new String[] {"반갑습니다.","ようこそ！","Welcome!","欢迎光临!"};
	
	@Autowired
	UserDAO dao;
	
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		logger.info("IN 로그아웃 페이지");
		session.removeAttribute("userName");
		return "redirect:login";
	}
	
	/**
	 * 로그인 페이지 진입
	 * @return
	 */
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String goLogin(){
		logger.info("IN 로그인 페이지");
		return "login";
	}
	
	/**
	 * 로그인 전송
	 * @return
	 */
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(JUser user, HttpSession session, Model model){
		logger.info("IN 로그인 전송");
		if(user.getPassword() == null){
			logger.info("부적절한 비밀번호 NULL로 로그인. 되돌아가기.");
			return "redirect:login";
		}
		JUser u = dao.selectUser(user);
		if(u != null){
			logger.info("로그인 성공");
			session.setAttribute("userName", u.getUsername());
			return "redirect:home";
		}
		model.addAttribute("message", "등록되지 않은 사용자 또는 잘못된 암호입니다.");
		model.addAttribute("url", "login");
		return "notice";
	}
	
	/**
	 * 회원 가입 페이지
	 * @return
	 */
	@RequestMapping(value="join", method = RequestMethod.GET)
	public String goJoin(){
		logger.info("IN 회원 가입 페이지");
		return "join";
	}
	
	/**
	 * 회원 가입 완료
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="join", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String join(JUser user, MultipartFile file){
		logger.info("IN 회원 가입 완료");
		if(user.getUsername() == null || user.getPassword() == null ||
				user.getEmail() == null){
			logger.info("회원 가입에 부족한 필드 발견");
			return "<!doctype html><html><head><title>회원 가입</title><body><script>alert('부족한 필드가 발견되어 회원 가입을 진행할 수 없습니다.');history.go(-1);</script></body></html>";
		}
		if(dao.insertUser(user)){
			logger.info("회원 가입 성공");
			return "<!doctype html><html><head><title>회원 가입 끝</title><body><script>alert('회원 가입에 성공했습니다. 로그인하세요.');location.href='login';</script></body></html>";
		} else {
			logger.info("회원 가입 DB 오류");
			return "<!doctype html><html><head><title>회원 가입</title><body><script>alert('DB에 회원 정보가 입력되지 않았습니다.');history.go(-1);</script></body></html>";
		}
	}
	
	/**
	 * 비동기 사용자 중복 확인
	 * @return
	 */
	@RequestMapping(value="vaildateId", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public @ResponseBody HashMap<String,Object> vaildateId(JUser user){
		logger.info("IN 사용자 아이디 검증 메서드");
		HashMap<String,Object> hm = new HashMap<>();
		JUser u = dao.selectUser(user);
		if(u != null){
			logger.info(user.getUsername()+" 사용자 아이디가 존재함");
			hm.put("result", "exists");
			return hm;
		} else {
			logger.info(user.getUsername()+" 사용자 아이디는 사용 가능함");
			hm.put("result", "ok");
			return hm;
		}
	}
	
	/**
	 * 정보 페이지
	 * @return
	 */
	@RequestMapping(value="info", method = RequestMethod.GET)
	public String goInfo(){
		return "info";
	}
	
	/**
	 * 비동기 정보 수정
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updateprofile", method = RequestMethod.POST)
	public String updateProfile(JUser user){
		//요청은 json으로 필드별로 하나씩 들어온다.
		return "";
	}
	
	/**
	 * 프로파일 페이지
	 * @return 로그인 상태가 아니면 400 BAD REQUEST를 반환한다.
	 */
	@RequestMapping(value="profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session, HttpServletResponse response){
		logger.info("IN profile 카드");
		if(session.getAttribute("userName") != null){
			logger.info("로그인 상태입니다.");
			JUser u = new JUser();
			u.setUsername((String)session.getAttribute("userName"));
			JUser ur = dao.selectUser(u);
			model.addAttribute("user",ur);
			model.addAttribute("greeting",greets[Math.round((float)Math.random()*3)]);
			return "profile";
		} else {
			logger.info("로그인 상태가 아닙니다.");
			try{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	}
}

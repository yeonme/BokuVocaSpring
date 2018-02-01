package me.yeon.bokuvoca.users.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import me.yeon.bokuvoca.HomeController;
import me.yeon.bokuvoca.common.utils.FileControl;
import me.yeon.bokuvoca.users.dao.UserDAO;
import me.yeon.bokuvoca.users.vo.JUser;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	String[] greets = new String[] {"반갑습니다.","ようこそ！","Welcome!","欢迎光临!"};
	final String uploadPath = "/profiles";
	
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
	public String goLogin(@CookieValue(value="Remember",required = false)Cookie rCookie, Model model, HttpSession session){
		logger.info("IN 로그인 페이지");
		
		if((String)session.getAttribute("userName") != null){
			return "redirect:/home";
		}
		
		if(rCookie != null){
			model.addAttribute("username",rCookie.getValue());
		}
		return "login";
	}
	
	/**
	 * 로그인 전송
	 * @return
	 */
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(JUser user, boolean remember, HttpSession session, Model model, HttpServletResponse response){
		logger.info("IN 로그인 전송");
		if(user.getPassword() == null){
			logger.info("부적절한 비밀번호 NULL로 로그인. 되돌아가기.");
			return "redirect:login";
		}
		JUser u = dao.selectUser(user);
		if(u != null){
			logger.info("로그인 성공");
			session.setAttribute("userName", u.getUsername());
			
			logger.info("아이디 기억하기 작업");
			Cookie cookie = new Cookie("Remember",user.getUsername());
			//리멤버 키로 아이디 저장
			cookie.setPath("/");
			if(remember){ //기억하는 경우 쿠키 만료 기간 잡기 (30일)
				cookie.setMaxAge(60*60*24*30);
			} else { //체크 해제시 이 쿠키 즉시 만료
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			
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
	@RequestMapping(value="join", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String join(JUser user, @RequestParam(value="file", required = false)MultipartFile file, Model model, HttpSession session){
		logger.info("IN 회원 가입 완료");
		if(user.getUsername() == null || user.getPassword() == null ||
				user.getEmail() == null){
			logger.info("회원 가입에 부족한 필드 발견");
			model.addAttribute("message","부족한 필드가 발견되어 회원 가입을 진행할 수 없습니다.");
			return "notice";
		}
		if(file != null && !file.isEmpty()){
			String originalFile = file.getOriginalFilename().toLowerCase();
			if(!(originalFile.endsWith(".jpg") || originalFile.endsWith(".gif") ||
					originalFile.endsWith(".png"))){
				logger.info("파일 확장자가 올바르지 않음");
				model.addAttribute("message", "파일 확장자는 jpg, gif, png 중 하나여야 합니다.");
				return "notice";
			}
			
			String username = user.getUsername();
			String nfile = FileControl.fileSave(file, username, uploadPath);
			user.setPhoto(nfile);
		}else{
			user.setPhoto(null);
		}
		if(!dao.insertUser(user)){
			logger.info("회원 가입 DB 오류");
			model.addAttribute("message", "DB에 회원 정보가 입력되지 않았습니다.");
			return "notice";
		}
		session.setAttribute("userName", user.getUsername());
		return "redirect:/";
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
	
	@RequestMapping(value="picture",method=RequestMethod.GET)
	public void picture(String photoname, HttpServletResponse response){
		// response의 Header영역에 파일 이름을 인코딩해서 등록
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(photoname, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 파일이 저장된 전체 경로
		String fullPath = uploadPath + "/" + photoname;
		// 서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력 스트림

		FileInputStream fis = null;
		ServletOutputStream sos = null;

		try {
			fis = new FileInputStream(fullPath);
			sos = response.getOutputStream();

			// 빠른 스트림 복사 powered by Spring
			FileCopyUtils.copy(fis, sos);
			fis.close();
			sos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

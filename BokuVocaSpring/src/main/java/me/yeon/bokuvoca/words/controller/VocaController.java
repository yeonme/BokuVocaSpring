package me.yeon.bokuvoca.words.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VocaController {
	/**
	 * home.jsp
	 * @return
	 */
	@RequestMapping(value="home", method = RequestMethod.GET)
	public String home(){
		return "home";
	}
	
	/**
	 * 퀴즈 시작점
	 * @return
	 */
	@RequestMapping(value="quiz", method = RequestMethod.GET)
	public String quiz(){
		return "quiz";
	}
	
	/**
	 * 본격적인 퀴즈
	 * @return
	 */
	@RequestMapping(value="quizplay", method = RequestMethod.GET)
	public String quizBegin(){
		//변수에 따라 다른 방식의 게임을 전개한다.
		return "quizplay";
	}
	
	/**
	 * 단어장
	 * @return
	 */
	@RequestMapping(value="voca", method = RequestMethod.GET)
	public String voca(){
		//단어장 페이지
		return "voca";
	}
}

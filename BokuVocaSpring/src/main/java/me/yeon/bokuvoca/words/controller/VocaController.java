package me.yeon.bokuvoca.words.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.yeon.bokuvoca.HomeController;
import me.yeon.bokuvoca.words.dao.VocaDAO;
import me.yeon.bokuvoca.words.vo.JWordItem;

@Controller
public class VocaController {

	private static final Logger logger = LoggerFactory.getLogger(VocaController.class);

	@Autowired
	VocaDAO dao;

	/**
	 * home.jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		if (session.getAttribute("userName") != null) {
			//model.addAttribute("rword", dao.randomWord());
			//랜덤은 word에서 자체적으로 구현하자.
			model.addAttribute("countWord", dao.countWord());
			return "home";
		} else {
			return "redirect:login";
		}
	}

	/**
	 * 퀴즈 시작점
	 * 
	 * @return
	 */
	@RequestMapping(value = "quiz", method = RequestMethod.GET)
	public String quiz() {
		return "quiz";
	}

	/**
	 * 본격적인 퀴즈
	 * 
	 * @return
	 */
	@RequestMapping(value = "quizplay", method = RequestMethod.GET)
	public String quizBegin() {
		// 변수에 따라 다른 방식의 게임을 전개한다.
		return "quizplay";
	}

	/**
	 * 단어장
	 * 
	 * @return
	 */
	@RequestMapping(value = "voca", method = RequestMethod.GET)
	public String voca() {
		// 단어장 페이지
		return "voca";
	}

	/**
	 * 단어 뜻
	 * 
	 * @return
	 */
	@RequestMapping(value = "word", method = RequestMethod.GET)
	public String word(JWordItem word, Model model, HttpServletResponse response) {
		// 단어 뜻 페이지
		int wnum = 0;
		if (word == null || word.getNum() <= 0) {
			/*try {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;*/
			wnum = dao.randomWord().getNum();
		} else {
			wnum = word.getNum();
		}
		JWordItem item = dao.selectDetailWord(wnum);
		if(item.getMeaning() != null){
			item.setMeaning(item.getMeaning().replaceAll("\n", "<br/>"));
		}
		model.addAttribute("word",item);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < item.getStar(); i++){
			sb.append("★");
		}
		model.addAttribute("star",sb.toString());
		if(item.getJlpt() != null){
			model.addAttribute("jlpt","N"+item.getJlpt()+"");
		}
		return "word";
	}
}

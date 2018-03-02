package me.yeon.bokuvoca.words.controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import me.yeon.bokuvoca.HomeController;
import me.yeon.bokuvoca.words.dao.VocaDAO;
import me.yeon.bokuvoca.words.vo.JVocaItem;
import me.yeon.bokuvoca.words.vo.JWordItem;
import net.sf.json.JSONArray;

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
	@RequestMapping(value = "quiz/*", method = RequestMethod.GET)
	public String quizBegin(HttpServletRequest request, Model model, HttpSession session) {
		// 변수에 따라 다른 방식의 게임을 전개한다.
		String path = (String) request.getAttribute(
	            HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE); //매핑 마스킹에 해당하는 현재 주소
	    System.out.println(path);
	    
	    ArrayList<JWordItem> questions = null;
	    switch(path){
	    	case "/quiz/rand": questions = dao.selectQWordAll(); break;
	    	case "/quiz/star0": questions = dao.selectQWordStar(0); break;
	    	case "/quiz/star1": questions = dao.selectQWordStar(1); break;
	    	case "/quiz/star2": questions = dao.selectQWordStar(2); break;
	    	case "/quiz/jlpt1": questions = dao.selectQWordJLPT("1"); break;
	    	case "/quiz/jlpt2": questions = dao.selectQWordJLPT("2"); break;
	    	case "/quiz/jlpt3": questions = dao.selectQWordJLPT("3"); break;
	    	case "/quiz/jlpt4": questions = dao.selectQWordJLPT("4"); break;
	    	case "/quiz/jlpt5": questions = dao.selectQWordJLPT("5"); break;
	    	case "/quiz/voca": questions = dao.selectQWordVoca((String)session.getAttribute("userName")); break;
	    }
	    
	    if(questions == null || questions.size() < 10){
	    	model.addAttribute("message", "단어장에 충분한 개수의 단어가 없어서 출제할 수 없습니다.");
	    	model.addAttribute("url","../quiz/");
	    	return "notice";
	    }
	    
	    logger.info("문제 생성 시작");
	    model.addAttribute("quiz",JSONArray.fromObject(questions));
	    
	    Random r = new Random();
	    ArrayList<HashMap<String,Object>> qlist = new ArrayList<>();
	    int qnum = 0;
	    for (JWordItem j : questions) {
	    	qnum++;
	    	int type = r.nextInt(2);
	    	if(j.getWord() == null){
	    		type = 1;
	    	}
	    	int answer = r.nextInt(5);
			ArrayList<JWordItem> bogi = null;
			if(type == 0){
				logger.info(qnum+". type 0 문제---"+j.getYomi());
				//독음 맞추기 "이 단어를 읽으면?"
				
				HashMap<String,Object> hmap = new HashMap<>();
				
				Pattern p = Pattern.compile("[\u3041-\u3096]*");
				Matcher m = p.matcher(j.getWord());
				int pos = -1;
				while(m.find()){
					if(m.group() != null && m.group().length() > 0){
						pos = m.start();
					}
				}
				if(pos > 0){
					//히라가나 범위가 검색되며 끝나는 경우 오쿠리가나로 지정하여 쿼리에 일치시킴
					hmap.put("okuri", j.getWord().substring(pos, j.getWord().length()));
				}
				
				hmap.put("length", j.getYomi().length());
				hmap.put("startswith", j.getYomi().charAt(0));
				hmap.put("answer", j.getYomi());
				hmap.put("type", j.getType());
				bogi = dao.listYomiByLength(hmap);
				if(bogi == null || bogi.size() < 4){
					logger.info("보기 미달 type 0: "+j.getYomi());
					//보기가 미달한 경우 legacy 보기 소환
					hmap.put("legacy", true);
					hmap.remove("okuri");
					bogi = dao.listYomiByLength(hmap);
					logger.info("재쿼리 후 결과: "+bogi.size());
				}
				answer = Math.min(answer, bogi.size());
				bogi.add(answer, j);
				//디버그용 반복문
				for(int i = 0; i < bogi.size(); i++){
					logger.info(i+"."+bogi.get(i).toString());
				}
			}else if(type ==1){
				logger.info(qnum+". type 1 문제---"+j.getYomi());
				//뜻 맞추기. "이 단어는 뭘까요?"
				if(j.getSummary() != null && j.getSummary().indexOf("→") > 0){
					//끝의 연관 단어 힌트를 없애버리자
					//단, 화살표로 시작하는 경우는 어쩔 수 없다. 스킵.
					j.setSummary(j.getSummary().substring(0,
							j.getSummary().indexOf("→")));
				}
				
				//뜻 맞추기
				HashMap<String,Object> hmap = new HashMap<>();
				hmap.put("length", j.getYomi().length()); //한자 길이에서 요미 길이로 변경
				hmap.put("wlength", j.getWord().length());
				hmap.put("startswith", j.getYomi().charAt(0));
				hmap.put("answer", j.getWord());
				hmap.put("type", j.getType());
				bogi = dao.listKanjiByLength(hmap);
				if(bogi == null || bogi.size() < 4){
					logger.info("보기 미달 type 1: "+j.getYomi());
					//보기가 미달한 경우 legacy 보기 소환
					hmap.put("legacy", true);
					bogi = dao.listKanjiByLength(hmap);
					logger.info("재쿼리 후 결과: "+bogi.size());
				}
				answer = Math.min(answer, bogi.size());
				bogi.add(answer, j);
				//디버그용 반복문
				for(int i = 0; i < bogi.size(); i++){
					if(bogi.get(i).getWord() != null && bogi.get(i).getWord().startsWith("(")){
						bogi.get(i).setWord(null);
					}					
					logger.info(i+"."+bogi.get(i).toString());
				}
			}
			HashMap<String,Object> each = new HashMap<>();
			each.put("type", type);
			each.put("answer", answer);
			each.put("bogi", bogi);
			qlist.add(each);
		}
	    
	    model.addAttribute("qlist",JSONArray.fromObject(qlist));
	    
		return "quizplay";
	}
	
	@ResponseBody
	@RequestMapping(value="report", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	public String quizResult(@RequestBody ArrayList<HashMap<String,Object>> report, HttpSession session){
		boolean result = true;
		for(HashMap<String,Object> hm : report){
			if(!(boolean)hm.get("correct")){
				JVocaItem voca = new JVocaItem();
				voca.setNum((int)hm.get("word"));
				voca.setUser((String)session.getAttribute("userName"));
				result = result && dao.newVoca(voca);
			};
		}
		
		return "{\"result\":\""+(result ? "ok" : "error")+"\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="vocaadd", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	public String vocaAdd(@RequestParam(value="num") String num, HttpSession session){
		JVocaItem voca = new JVocaItem();
		voca.setNum(Integer.parseInt(num));
		voca.setUser((String)session.getAttribute("userName"));
		return "{\"result\":\""+(dao.newVoca(voca) ? "ok" : "error")+"\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="vocadel", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
	public String vocaDel(@RequestParam(value="num") String num, HttpSession session){
		JVocaItem voca = new JVocaItem();
		voca.setNum(Integer.parseInt(num));
		voca.setUser((String)session.getAttribute("userName"));
		return "{\"result\":\""+(dao.deleteVoca(voca) ? "ok" : "error")+"\"}";
	}

	/**
	 * 단어장
	 * 
	 * @return
	 */
	@RequestMapping(value = "voca", method = RequestMethod.GET)
	public String voca(@RequestParam(value="page", required = false, defaultValue="1")int page, HttpSession session,
			Model model) {
		// 단어장 페이지
		HashMap<String,Object> hmap = new HashMap<>();
		String username = (String)session.getAttribute("userName");
		hmap.put("user", username);
		hmap.put("startitem", (page-1)*20+1);
		hmap.put("count", 20);
		model.addAttribute("vocalist", dao.selectVoca(hmap));
		int total = dao.countVoca(username);
		model.addAttribute("total", total);
		model.addAttribute("cpage", page);
		model.addAttribute("tpage", Math.ceil(total / 20.0));
		return "voca";
	}

	/**
	 * 단어 뜻
	 * 
	 * @return
	 */
	@RequestMapping(value = "word", method = RequestMethod.GET)
	public String word(JWordItem word, @RequestParam(value="nojump", required = false)boolean nojump, Model model, HttpServletResponse response, HttpSession session) {
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
		JVocaItem v = new JVocaItem();
		v.setNum(item.getNum());
		v.setUser((String)session.getAttribute("userName"));
		JVocaItem voca = dao.hasVoca(v);
		model.addAttribute("isWorded", voca != null);
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
	
	@ResponseBody
	@RequestMapping(value="autoComplete", method = RequestMethod.GET, produces="text/html; charset=UTF-8")
	public String autoComplete(String input){
		if(input == null || input.length() == 0){
			return null;
		}
		ArrayList<JWordItem> jw = dao.selectListWord(input);
		StringBuilder sb = new StringBuilder();
		for (JWordItem jWordItem : jw) {
			sb.append("<li class=\"collection-item\"><a href=\"javascript:\" class=\"autocomp-list\" data-num=\""+jWordItem.getNum()+"\" data-yomi=\""+jWordItem.getYomi()+"\"><span class=\"title\">");
			sb.append(jWordItem.getYomi() == null?"":jWordItem.getYomi());
			sb.append("</span>&nbsp;<span class=\"text-small\">");
			sb.append(jWordItem.getWord() == null?"":jWordItem.getWord());
			sb.append("<span>");
			if(jWordItem.getStar() > 0) {
				sb.append("<a href=\"#!\" class=\"secondary-content\">");
				for (int i = 0; i < jWordItem.getStar(); i++) {
					sb.append("<i class=\"material-icons\">grade</i>");
				}
				sb.append("</a>");
			}
			sb.append("</li></a>");
		}
		if(jw.size() > 0){
			sb.append("<script>"+
			"search($('.autocomp-list').first().attr('data-num'));"+
	  "$(\".autocomp-list\").on(\"click\", function(){"+
	  "search($(this).attr('data-num'),$(this).attr('data-yomi'));"+
	  "$('html, body').animate({scrollTop:$('#word_result').offset().top});"+
	  "});</script>");
		}
		return sb.toString();
	}
}

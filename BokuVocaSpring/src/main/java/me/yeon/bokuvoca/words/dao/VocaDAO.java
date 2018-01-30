package me.yeon.bokuvoca.words.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.yeon.bokuvoca.users.controller.UserController;
import me.yeon.bokuvoca.users.dao.UserMapper;
import me.yeon.bokuvoca.users.vo.JUser;
import me.yeon.bokuvoca.words.vo.JVocaItem;
import me.yeon.bokuvoca.words.vo.JWordItem;

@Repository
public class VocaDAO {
	private static final Logger logger = LoggerFactory.getLogger(VocaDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	
	public JWordItem randomWord(){
		logger.info("IN selectUser");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		JWordItem result = null;
		try{
			result = mapper.randomWord();
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT selectUser");
		return result;
	}
	
	public JWordItem selectDetailWord(int num){
		logger.info("IN selectDetailWord");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		JWordItem result = null;
		try{
			result = mapper.selectDetailWord(num);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT selectDetailWord");
		return result;
	}
	
	public int countWord(){
		logger.info("IN countWord");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		int result = -1;
		try{
			result = mapper.countWord();
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT countWord");
		return result;
	}
	
	public ArrayList<JWordItem> selectListWord(String keyword){
		logger.info("IN selectListWord");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		ArrayList<JWordItem> result = null;
		try{
			result = mapper.selectListWord(keyword);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT selectListWord");
		return result;
	}
	
	public ArrayList<JWordItem> selectQWordAll(){
		logger.info("IN selectQWordAll");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		ArrayList<JWordItem> result = null;
		try{
			result = mapper.selectQWordAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT selectQWordAll");
		return result;
	}
	public ArrayList<JWordItem> selectQWordJLPT(String jlpt){
		logger.info("IN selectQWordJLPT");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		ArrayList<JWordItem> result = null;
		try{
			result = mapper.selectQWordJLPT(jlpt);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT selectQWordJLPT");
		return result;
	}
	public ArrayList<JWordItem> selectQWordStar(int star){
		logger.info("IN selectQWordStar");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		ArrayList<JWordItem> result = null;
		try{
			result = mapper.selectQWordStar(star);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT selectQWordStar");
		return result;
	}
	public ArrayList<JWordItem> selectQWordVoca(String user){
		logger.info("IN selectQWordVoca");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		ArrayList<JWordItem> result = null;
		try{
			result = mapper.selectQWordVoca(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT selectQWordVoca");
		return result;
	}
	public ArrayList<JWordItem> listYomiByLength(HashMap<String,Object> hmap){
		logger.info("IN listYomiByLength");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		ArrayList<JWordItem> result = null;
		try{
			result = mapper.listYomiByLength(hmap);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT listYomiByLength");
		return result;
	}
	public ArrayList<JWordItem> listKanjiByLength(HashMap<String,Object> hmap){
		logger.info("IN listKanjiByLength");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		ArrayList<JWordItem> result = null;
		try{
			result = mapper.listKanjiByLength(hmap);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT listKanjiByLength");
		return result;
	}
	public boolean newVoca(JVocaItem voca){
		logger.info("IN newVoca");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		boolean result = false;
		try{
			result = mapper.newVoca(voca)>0;
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT newVoca");
		return result;
	}
	public boolean deleteVoca(JVocaItem voca){
		logger.info("IN deleteVoca");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		boolean result = false;
		try{
			result = mapper.deleteVoca(voca)>0;
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT deleteVoca");
		return result;
	}
	public JVocaItem hasVoca(JVocaItem voca){
		logger.info("IN hasVoca");
		VocaMapper mapper = sqlSession.getMapper(VocaMapper.class);
		JVocaItem result = null;
		try{
			result = mapper.hasVoca(voca);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT hasVoca");
		return result;
	}
}

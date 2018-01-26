package me.yeon.bokuvoca.words.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.yeon.bokuvoca.users.controller.UserController;
import me.yeon.bokuvoca.users.dao.UserMapper;
import me.yeon.bokuvoca.users.vo.JUser;
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
	
	public boolean updateLogin(JUser user){
		logger.info("IN updateLogin");
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		boolean result = false;
		try{
			result = mapper.updateLogin(user) > 0;
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("OUT updateLogin");
		return result;
	}
}

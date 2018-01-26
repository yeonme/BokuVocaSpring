package me.yeon.bokuvoca.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.yeon.bokuvoca.users.controller.UserController;
import me.yeon.bokuvoca.users.vo.JUser;

@Repository
public class UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	
	public JUser selectUser(JUser user){
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		JUser result = null;
		try{
			result = mapper.selectUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateLogin(JUser user){
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		boolean result = false;
		try{
			result = mapper.updateLogin(user) > 0;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean insertUser(JUser user){
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		boolean result = false;
		try{
			result = mapper.insertUser(user) > 0;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}

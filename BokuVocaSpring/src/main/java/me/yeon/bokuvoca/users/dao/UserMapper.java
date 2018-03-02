package me.yeon.bokuvoca.users.dao;

import me.yeon.bokuvoca.users.vo.JUser;

public interface UserMapper {
	public JUser selectUser(JUser user);
	public int updateLogin(JUser user);
	public int insertUser(JUser user);
}

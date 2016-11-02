package com.fuandren.dao;

import com.fuandren.domain.User;

public interface UserDao {

	//从javabean中接受封装好的数据，向数据库中执行插入操作
	void add(User user);

	User find(String username, String password);

	boolean find(String username);

}
package com.fuandren.service;

import com.fuandren.domain.User;
import com.fuandren.exception.UserExistException;

public interface BusinessService {

	//为web层提供注册服务
	void registerUser(User user) throws UserExistException;

	//为web层提供登陆服务
	User loginUser(String username, String password);

}
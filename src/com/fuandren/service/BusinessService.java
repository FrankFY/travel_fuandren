package com.fuandren.service;

import com.fuandren.domain.User;
import com.fuandren.exception.UserExistException;

public interface BusinessService {

	//Ϊweb���ṩע�����
	void registerUser(User user) throws UserExistException;

	//Ϊweb���ṩ��½����
	User loginUser(String username, String password);

}
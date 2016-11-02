package com.fuandren.service.impl;

import java.util.List;

import com.fuandren.dao.UserDao;
import com.fuandren.dao.impl.UserDaoImpl;
import com.fuandren.domain.User;
import com.fuandren.exception.UserExistException;
import com.fuandren.service.BusinessService;
import com.fuandren.utils.ServiceUtils;

public class BusinessServiceImpl implements BusinessService {

	//�˴������ù���ģʽ����
	private UserDao userdao = new UserDaoImpl();
	
	//Ϊweb���ṩע�����
	public void registerUser(User user) throws UserExistException{
		boolean b = userdao.find(user.getUsername());
		if(b){
			throw new UserExistException();
		}else{
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			userdao.add(user);
		}
	}
	
	//Ϊweb���ṩ��½����
	public User loginUser(String username, String password){
		password = ServiceUtils.md5(password);
		return userdao.find(username, password);
	}
}

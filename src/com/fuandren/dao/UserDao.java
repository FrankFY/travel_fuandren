package com.fuandren.dao;

import com.fuandren.domain.User;

public interface UserDao {

	//��javabean�н��ܷ�װ�õ����ݣ������ݿ���ִ�в������
	void add(User user);

	User find(String username, String password);

	boolean find(String username);

}
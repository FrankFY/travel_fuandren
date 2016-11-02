package com.fuandren.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fuandren.dao.UserDao;
import com.fuandren.domain.User;
import com.fuandren.exception.DaoException;
import com.fuandren.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	//��javabean�н��ܷ�װ�õ����ݣ������ݿ���ִ�в������
	public void add(User user){
		//�������ڲ������ݿ�Ķ��󲢳�ʼ��������ڴ�
		Connection conn = null;		//�ö������ڴ�������
		PreparedStatement st = null;	//�ö������ڵ���sql���
		ResultSet rs = null;		//��������ö������ڱ����ѯ���
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into users(id,username,password) values(?,?,?)";
			//Ԥ����sql
			st = conn.prepareStatement(sql);
			st.setString(1, user.getId());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPassword());
			int num = st.executeUpdate();
			if(num<1){
				throw new RuntimeException("ע���û�ʧ��!!!");
			}
		} catch (Exception e) {
			//����ʱ�쳣
			throw new DaoException(e);
		}
		finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	public User find(String username,String password){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from users where username=? and password=?";
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			rs = st.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			
			return null;
			
		} catch (Exception e) {
			//����ʱ�쳣
			throw new DaoException(e);
		} finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
	
	public boolean find(String username){
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from users where username=?";
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			rs = st.executeQuery();
			if(rs.next()){
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			//����ʱ�쳣
			throw new DaoException(e);
		} finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
}

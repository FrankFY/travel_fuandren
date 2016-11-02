package com.fuandren.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fuandren.dao.UserDao;
import com.fuandren.domain.User;
import com.fuandren.exception.DaoException;
import com.fuandren.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	//从javabean中接受封装好的数据，向数据库中执行插入操作
	public void add(User user){
		//创建用于操作数据库的对象并初始化，清空内存
		Connection conn = null;		//该对象用于创建连接
		PreparedStatement st = null;	//该对象用于调用sql语句
		ResultSet rs = null;		//结果集，该对象用于保存查询结果
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into users(id,username,password) values(?,?,?)";
			//预编译sql
			st = conn.prepareStatement(sql);
			st.setString(1, user.getId());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPassword());
			int num = st.executeUpdate();
			if(num<1){
				throw new RuntimeException("注册用户失败!!!");
			}
		} catch (Exception e) {
			//运行时异常
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
			//运行时异常
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
			//运行时异常
			throw new DaoException(e);
		} finally{
			JdbcUtils.release(conn, st, rs);
		}
	}
}

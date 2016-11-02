package com.fuandren.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	//����.properties�����ļ��������ݿ�����,ʹ��static��֤�������ֻ����һ��
	//config������java�Դ���utils�������е�һ���������ʵ�������ڶ�ȡproperties�����ļ�
	private static Properties config = new Properties();                                   
	static{
		try {
			//�����Դ���load()����
			//���÷��佫properties�����ļ����ص���������ֽ����ļ���
			config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			//���÷����ȡ�����ļ��е�driver��ֵ�Զ�Ӧ�������������ص�������ֽ����ļ���
			Class.forName(config.getProperty("driver"));
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	//���徲̬����������properties�����ļ��е������Ϣ
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(config.getProperty("url"), config.getProperty("username"), config.getProperty("password"));
		
	}
	//�����ͷŷ�����ִ���ͷ����ݿ����ӵĲ�������ֹ���ݿ���Դռ��
	public static void release(Connection conn,Statement st,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(st!=null){
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

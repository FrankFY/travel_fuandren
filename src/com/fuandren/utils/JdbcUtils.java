package com.fuandren.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
	//利用.properties配置文件加载数据库驱动,使用static保证类加载器只加载一次
	//config对象是java自带的utils工具类中的一个工具类的实例，用于读取properties配置文件
	private static Properties config = new Properties();                                   
	static{
		try {
			//调用自带的load()方法
			//利用反射将properties配置文件加载到虚拟机的字节码文件中
			config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			//利用反射获取配置文件中的driver键值对对应的驱动名，加载到虚拟机字节码文件中
			Class.forName(config.getProperty("driver"));
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	//定义静态方法，返回properties配置文件中的相关信息
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(config.getProperty("url"), config.getProperty("username"), config.getProperty("password"));
		
	}
	//定义释放方法，执行释放数据库链接的操作，防止数据库资源占用
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

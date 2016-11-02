package com.fuandren.utils;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
	// RegisterForm.class
	public static <T> T request2Bean(HttpServletRequest request,
			Class<T> beanCalss) {

		try {
			// 1.创建要封装数据的bean
			T bean = beanCalss.newInstance();

			// 2.把request中的数据整到bean中
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement(); // username password
														// email birthday
				String value = request.getParameter(name); // aaaa 123 aa@
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * private String username; private String password; private String
	 * password2; private String email; private String birthday; private String
	 * nickname; private Map errors = new HashMap();
	 * 
	 * private String id; private String username; private String password;
	 * private String email; private Date birthday; private String nickname;
	 */

	public static void copyBean(Object src, Object dest) {
		try {
			BeanUtils.copyProperties(dest, src); // beanUtils只支持8种基本数据类型的转换，所以要写一个方法将String转换为date
		} catch (Exception e) {
			throw new RuntimeException(e); // 一律转成运行时异常外抛
		}
	}

	// 产生全球唯一的id
	public static String generateID() {
		return UUID.randomUUID().toString();
	}

}

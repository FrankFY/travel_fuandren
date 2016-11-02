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
			// 1.����Ҫ��װ���ݵ�bean
			T bean = beanCalss.newInstance();

			// 2.��request�е���������bean��
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
			BeanUtils.copyProperties(dest, src); // beanUtilsֻ֧��8�ֻ����������͵�ת��������Ҫдһ��������Stringת��Ϊdate
		} catch (Exception e) {
			throw new RuntimeException(e); // һ��ת������ʱ�쳣����
		}
	}

	// ����ȫ��Ψһ��id
	public static String generateID() {
		return UUID.randomUUID().toString();
	}

}

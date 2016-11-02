package com.fuandren.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fuandren.domain.User;
import com.fuandren.service.BusinessService;
import com.fuandren.service.impl.BusinessServiceImpl;
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		BusinessService service = new BusinessServiceImpl();
		User user = service.loginUser(username, password);
		if(user!=null){
			request.getSession().setAttribute("user", user);
			//让用户登陆成功后，跳转首页 
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}
		
		request.setAttribute("error_login", "用户名或密码错误!!!");   
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return;
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

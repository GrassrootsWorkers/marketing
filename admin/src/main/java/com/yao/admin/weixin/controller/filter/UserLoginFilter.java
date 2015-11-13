package com.yao.admin.weixin.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class UserLoginFilter implements Filter{
	
	public static final String LOGIN_URL = "http://manage.111.com.cn";
	
	
	private static Logger log = Logger.getLogger(UserLoginFilter.class);
	
	@Override
	public void destroy() {
		log.info("UserLoginFilter.destroy.................");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
        
        HttpSession session = ((HttpServletRequest)request).getSession();
       if (isLogin(request, response, session)) {
        	chain.doFilter(request, response);
        }
		else {
			((HttpServletResponse)response).sendRedirect(LOGIN_URL);
		}
	}
	
	

	private boolean isLogin(ServletRequest request, ServletResponse response, HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		if((userName == null || "".equals(userName)) && ((HttpServletRequest)request).getRequestURI().indexOf("/bill/login")<0) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println(".....初始化登录权限： com.yao.open.control.filter.UserLoginFilter.....");
	}
}

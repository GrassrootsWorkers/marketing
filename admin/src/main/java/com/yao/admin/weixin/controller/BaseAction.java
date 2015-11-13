/**
 * 
 */
package com.yao.admin.weixin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseAction {

	protected static final Integer PAGESIZE = new Integer(10);

	protected int pageSize = 10;

	protected int pageIndex = 1;
	protected HttpSession session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	public void clearUserSessionInfo() {
		session.setAttribute("userName", null);
	}

	public String getCookieValue(String key) {
		Cookie cookie = this.getCookie(key);
		if (cookie != null) {
			try {
				return URLDecoder.decode(cookie.getValue(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据键获取对应的cookie对象
	 * 
	 * @param key
	 *            对应的key
	 * 
	 * @return key对应的cookie值
	 */
	public Cookie getCookie(String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length < 1)
			return null;
		for (Cookie temp : cookies) {
			if (temp.getName().equals(key)) {
				return temp;
			}
		}
		return null;
	}

	public void clearCookie(String key) {
		Cookie cookie = new Cookie(key, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}

package com.yao.admin.weixin.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.StringUtils;
import com.yao.admin.weixin.controller.BaseAction;
import com.yao.common.business.impl.CommonManagerImpl;
import com.yao.common.business.impl.CommonManagerImplServiceLocator;


@Controller
public class LoginAction extends BaseAction{

	private  static Logger log = Logger.getLogger(LoginAction.class.getName());
	public static final String USER_LOGIN_NAME = "user_login_name";
	private String user_login_name;
	private String userName;
	private CommonManagerImpl manager;
	private String selectFunc;
	private String status;
	private String user_code;
	private String timeStr; //时间字符串 长度为6(194655)


	public LoginAction() {
		try {
			manager = new CommonManagerImplServiceLocator().getCommonManagerImpl();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("login")
	public String login(String user_code) {
		if (!StringUtils.isNullOrEmpty(user_code)) {
			userName = user_code;
		} else {
			userName = getCookieValue(USER_LOGIN_NAME);
		}
		
		if (StringUtils.isNullOrEmpty(userName)) {
			return "redirect:http://manage.111.com.cn";
		}
		//HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(!userName.equals((String) session.getAttribute("userName"))) {
			session.setAttribute("userName",user_code);
		}
		
		try {
			wb(user_code);
		} catch (Exception e) {
		
			return "redirect:http://manage.111.com.cn";
		}
		log.info("登陆成功");
		return "common/index";
	}
	
	@RequestMapping("logout")
	public void loginout() {
		clearUserSessionInfo();
		clearCookie(userName);
	}
	private void wb(String username) throws Exception {

		selectFunc = manager.getFuncs(username,"69B088A56B814A9C8EFD9BAA8D21B7FE");

		session.setAttribute("urls", selectFunc);
		
		session.setAttribute("userFunc", manager.getFuncList(username,"69B088A56B814A9C8EFD9BAA8D21B7FE"));
	}
		
	public String getUser_login_name() {
		return user_login_name;
	}

	public void setUser_login_name(String user_login_name) {
		this.user_login_name = user_login_name;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUser_code() {
		return user_code;
	}


	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	

	public String getTimeStr() {
		return timeStr;
	}


	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
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

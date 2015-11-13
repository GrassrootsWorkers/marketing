/**
 *
 */
package com.yao.front.weixin.controller;

import com.yao.marketing.config.PassportConfig;
import com.yao.marketing.passport.PassportEncryption;
import com.yao.marketing.string.StringUtil;
import com.yao.passport.model.customer.EcuserCustomer;
import com.yao.passport.model.customer.MobileUserInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class BaseAction {

		protected static final Integer PAGESIZE = new Integer(10);

		protected int pageSize = 10;

		protected int pageIndex = 1;
		protected HttpSession session;
		protected HttpServletRequest request;
		protected HttpServletResponse response;

		@ModelAttribute
		public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
				this.request = request;
				this.response = response;
				this.session = request.getSession();
		}
		protected void setUserInfoToCookie(MobileUserInfo mu, int type) throws Exception {
				EcuserCustomer userInfo = mu.getCustomer();
				String security = PassportEncryption.getSecurity(mu.getToken(), userInfo.getEcUserId().toString(),
								URLDecoder.decode(userInfo.getId(), "UTF-8"));
				String value = "UserId=" + userInfo.getEcUserId() + "&UserName=" + userInfo.getId() + "&NickName="
								+ userInfo.getNickName() + "&Token=" + mu.getToken() + "&Security=" + security + "&userLeverId="
								+ userInfo.getUserLevelId();
				if (type == 1) {
						addCookie("JUM", userInfo.getId(), ".111.com.cn", PassportConfig.getConfigIntVal("userinfoweek_expire"));
						addCookie("JUD", String.valueOf(userInfo.getEcUserId()), ".111.com.cn",
										PassportConfig.getConfigIntVal("userinfoweek_expire"));
						if (userInfo.getNickName() != null) {
								if (userInfo.getNickName().length() > 0) {
										addCookie("JUN", userInfo.getNickName(), ".111.com.cn",
														PassportConfig.getConfigIntVal("userinfoweek_expire"));
								} else {
										addCookie("JUN", userInfo.getId(), ".111.com.cn",
														PassportConfig.getConfigIntVal("userinfoweek_expire"));
								}
						} else {
								addCookie("JUN", userInfo.getId(), ".111.com.cn", PassportConfig.getConfigIntVal("userinfoweek_expire"));
						}

						addCookie("UserInfo", value, ".111.com.cn", PassportConfig.getConfigIntVal("userinfoweek_expire"));

				} else {
						addCookie("JUM", userInfo.getId(), ".111.com.cn", PassportConfig.getConfigIntVal("junthree_expire"));
						addCookie("JUD", String.valueOf(userInfo.getEcUserId()), ".111.com.cn", 0);
						if (userInfo.getNickName() != null) {
								if (userInfo.getNickName().length() > 0) {
										addCookie("JUN", userInfo.getNickName(), ".111.com.cn", PassportConfig.getConfigIntVal("junthree_expire"));
								} else {
										addCookie("JUN", userInfo.getId(), ".111.com.cn", PassportConfig.getConfigIntVal("junthree_expire"));
								}
						} else {
								addCookie("JUN", userInfo.getId(), ".111.com.cn", PassportConfig.getConfigIntVal("junthree_expire"));
						}

						addCookie("UserInfo", value, ".111.com.cn", 0);
				}
		}
		public String[] sortStrings(String[] strArr) {
				Arrays.sort(strArr, new Comparator<String>() {
						@Override
						public int compare(String o1, String o2) {
								return o1.compareTo(o2);
						}
				});
				return strArr;
		}

		protected String getCookieUserName(String cookieKey) {
				String userInfo = getCookieValue(cookieKey);
				if(userInfo == null ) return null;
				Map<String,Object> keyValue = StringUtil.parseStrToMap(userInfo, "&", "=");
				return (String)keyValue.get("UserName");
		}

		protected Map<String,Object> getUserInfo(String userinfoStr) {
				if(userinfoStr == null ) return null;
				Map<String,Object> keyValue = StringUtil.parseStrToMap(userinfoStr, "&", "=");
			 return keyValue;
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
		 * @param key 对应的key
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

		public void addCookie(String key, String value, String domain, int time) {
			try {
				if(null!= value){
					value=URLEncoder.encode(value, "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        Cookie cookie = new Cookie(key, value);
	        cookie.setPath("/");// 这个要设置
	        // 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
	        if(time > 0){
	            cookie.setMaxAge(time);
	        }
	        if(domain != null){
	            cookie.setDomain(domain);
	        }
	        this.response.addCookie(cookie);
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

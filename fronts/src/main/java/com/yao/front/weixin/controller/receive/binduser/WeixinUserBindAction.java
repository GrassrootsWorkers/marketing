package com.yao.front.weixin.controller.receive.binduser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yao.front.weixin.controller.BaseAction;
import com.yao.marketing.config.PassportConfig;
import com.yao.marketing.interfaces.weixin.keywords.IKeywordsManager;
import com.yao.marketing.interfaces.weixin.user.IWeixinUserBindedManager;
import com.yao.marketing.passport.PassportEncryption;
import com.yao.marketing.string.StringUtil;
import com.yao.model.weixin.user.WeixinUserBind;
import com.yao.passport.model.customer.EcuserCustomer;
import com.yao.passport.model.customer.MobileUserInfo;
import com.yao.passport.remote.interfaces.MobileSCustomerManager;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WeixinUserBindAction extends BaseAction {

		private String openCookieKey = "OPENID";
		@Autowired
		IKeywordsManager keywordsManager;
		@Autowired
		IWeixinUserBindedManager weixinbindedManager;
		@Autowired
		MobileSCustomerManager passportManager;

		@RequestMapping(value = "/bind/{shopId}/{venderId}", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, String> login(String username, String password, Integer type, String openId, String handle, @PathVariable String venderId, @PathVariable Integer shopId) {
				Map<String, String> returnMap = new HashMap<String, String>();
				if (username == null || password == null) {
						returnMap.put("error", "请正确填写信息");
						return returnMap;
				}
				// 验证用户是否是微信用户
				if ("add".equals(handle)) {
						if (weixinbindedManager.getBindedOfficialUsereId(openId) != null) {
								returnMap.put("error", "已绑定");
								return returnMap;
						}
				}

				MobileUserInfo userInfo = null;
				try {
						username = URLDecoder.decode(username, "UTF-8");
						userInfo = passportManager.remoteLogin(username, password);
				} catch (UnsupportedEncodingException e) {
						returnMap.put("error", "系统错误");
						return returnMap;
				}
				int result = userInfo.getResult();
				switch (result) {
						case 1:
								try {
										//两周免登陆
										setUserInfoToCookie(userInfo, type);
										WeixinUserBind bindUser = new WeixinUserBind();
										bindUser.setOpenId(openId);
										bindUser.setUserId(username);
										bindUser.setShopId(shopId);
										bindUser.setVenderId(venderId);
										if ("delete".equals(handle)) {
												weixinbindedManager.delete(username, openId, shopId);
										}
										Integer id = weixinbindedManager.insert(bindUser);
										if (id <= 0) {
												returnMap.put("error", "系统错误");
										} else {
												returnMap.put("success", "绑定成功");
										}
								} catch (Exception e) {
										e.printStackTrace();
										returnMap.put("error", "系统错误");
								}

								break;
						case 2:
								returnMap.put("error", "参数错误");
								break;
						case 3:
								returnMap.put("error", "用户不存在");
								break;
						case 4:
								returnMap.put("error", "用户被禁用");
								break;
						case 5:
								returnMap.put("error", "密码错误");
								break;
						default:
								returnMap.put("error", "系统错误");
				}
				return returnMap;
		}



		@RequestMapping(value = "/unbind/{shopId}", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, String> unbind(String username, String openId, @PathVariable Integer shopId) {
				Map<String, String> returnMap = new HashMap<String, String>();
				try {
						username = URLDecoder.decode(username, "UTF-8");
						weixinbindedManager.delete(username, openId, shopId);
						returnMap.put("success", "解绑成功");
				} catch (UnsupportedEncodingException e) {
						returnMap.put("error", "解绑失败");
				}

				return returnMap;
		}

		@RequestMapping(value = "/page/{shopId}/{venderId}/{openId}/{flag}", method = RequestMethod.GET)
		public ModelAndView page(HttpServletRequest request, HttpServletResponse response, @PathVariable String shopId,
														 @PathVariable String venderId, @PathVariable String openId, @PathVariable String flag) {
				ModelAndView mv = new ModelAndView();
				mv.addObject("shopId", shopId);
				mv.addObject("venderId", venderId);
				mv.addObject("openId", openId);
				mv.addObject("handle", "add");
				String returnUrl = null;
				if ("unbind".equals(flag)) {
						String userName = weixinbindedManager.getBindedOfficialUsereId(openId);
						if (StringUtil.isNullorBlank(userName)) {
								mv.setViewName("user/user_login_wap");
						} else {
								mv.addObject("username", userName);
								mv.setViewName("user/user_unbind_wap");
						}
						returnUrl = "http://weixin.111.com.cn/mw/user/jointLogin/" + shopId + "/" + venderId + "/" + openId + "/delete/";
				} else {
						returnUrl = "http://weixin.111.com.cn/mw/user/jointLogin/" + shopId + "/" + venderId + "/" + openId + "/add/";
						mv.setViewName("user/user_login_wap");
				}

				this.writeCookie(openId, returnUrl);
				return mv;
		}

		@RequestMapping(value = "/change/{shopId}/{venderId}/{openId}", method = RequestMethod.GET)
		public ModelAndView change(HttpServletRequest request, HttpServletResponse response, @PathVariable String shopId,
															 @PathVariable String venderId, @PathVariable String openId) {
				ModelAndView mv = new ModelAndView();
				mv.addObject("shopId", shopId);
				mv.addObject("venderId", venderId);
				mv.addObject("openId", openId);
				mv.addObject("handle", "delete");
				mv.setViewName("user/user_login_wap");
				String returnUrl = "http://weixin.111.com.cn/mw/user/jointLogin/" + shopId + "/" + venderId + "/" + openId + "/delete/";
				this.writeCookie(openId, returnUrl);
				return mv;
		}

		public void writeCookie(String openId, String returnUrl) {
				addCookie(openCookieKey, openId, ".111.com.cn", 0);
				addCookie("ReturnUrl", returnUrl, ".111.com.cn", 0);

		}

		@RequestMapping(value = "/user/jointLogin/{shopId}/{venderId}/{openId}/{handle}/", method = RequestMethod.GET)
		@ResponseBody
		public ModelAndView jointLogin(@PathVariable Integer shopId, @PathVariable String venderId, @PathVariable String openId, @PathVariable String handle) {
				Map<String, String> returnMap = new HashMap<String, String>();
				String cookieOpenId = getCookieValue(openCookieKey);
				if (StringUtil.isNullorBlank(cookieOpenId)) {
						returnMap.put("flag", "error");
						return new ModelAndView(new RedirectView("/html/bind_faile.html"));

				}
				if(!cookieOpenId.equals(openId)){
						return new ModelAndView(new RedirectView("/html/bind_faile.html"));
				}
				if ("add".equals(handle)) {
						if (weixinbindedManager.getBindedOfficialUsereId(openId) != null) {
								return new ModelAndView(new RedirectView("/html/bind_success.html"));
						}
				}
				String username = this.getCookieUserName("UserInfo");
				if(username == null){
						return new ModelAndView(new RedirectView("/html/bind_faile.html"));
				}
				WeixinUserBind bindUser = new WeixinUserBind();
				bindUser.setOpenId(openId);
				bindUser.setUserId(username);
				bindUser.setShopId(shopId);
				bindUser.setVenderId(venderId);
				if ("delete".equals(handle)) {
						weixinbindedManager.delete(username, openId, shopId);
				}
				Integer id = weixinbindedManager.insert(bindUser);
				if (id <= 0) {
						return new ModelAndView(new RedirectView("/html/bind_faile.html"));
				}
				return new ModelAndView(new RedirectView("/html/bind_success.html"));
		}



				public IKeywordsManager getKeywordsManager() {
				return keywordsManager;
		}

		public void setKeywordsManager(IKeywordsManager keywordsManager) {
				this.keywordsManager = keywordsManager;
		}

		public MobileSCustomerManager getPassportManager() {
				return passportManager;
		}

		public void setPassportManager(MobileSCustomerManager passportManager) {
				this.passportManager = passportManager;
		}

}

package com.yao.front.weixin.controller.user;

import com.yao.front.weixin.controller.BaseAction;
import com.yao.passport.model.customer.MobileUserInfo;
import com.yao.passport.remote.interfaces.MobileSCustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserAction extends BaseAction {
		@Autowired
		MobileSCustomerManager passportManager;

		@RequestMapping(value = "/user/login/", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, String> userLogin(String username, String password, Integer type) {
				;
				Map<String, String> map = new HashMap<String, String>();
				MobileUserInfo userInfo = null;
				try {
						username = URLDecoder.decode(username, "UTF-8");
						userInfo = passportManager.remoteLogin(username, password);
						int result = userInfo.getResult();
						switch (result) {
								case 1:
										//¡Ω÷‹√‚µ«¬Ω
										setUserInfoToCookie(userInfo, type);
										map.put("flag", "success");
										break;
								default:
										map.put("flag", result + "");
						}
				} catch (UnsupportedEncodingException e) {
						map.put("flag", "error");
				} catch (Exception e) {
						map.put("flag", "error");
				}
				return map;
		}
}

package com.yao.front.weixin.controller.receive.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yao.front.weixin.controller.BaseAction;
import com.yao.marketing.config.ReloadableConfig;
import com.yao.marketing.config.SHAUtil;
import com.yao.marketing.interfaces.weixin.keywords.IKeywordsManager;
import com.yao.marketing.web.WebUtils;
import com.yao.model.convert.WeixinDataConvert;
import com.yao.model.weixin.receive.ReceivedMessage;
import com.yao.model.weixin.send.SendedMessage;

@Controller
public class WeixinMessageReceiveAction extends BaseAction {

	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private String token;
	private Logger log = Logger.getLogger(WeixinMessageReceiveAction.class);
	@Autowired
	IKeywordsManager keywordsManager;
	@RequestMapping(value = "receiveMsg/{shopNum}/{venderId}", method = RequestMethod.GET)
	public void receiveMsg(HttpServletRequest request, HttpServletResponse response, @PathVariable String shopNum,
			@PathVariable String venderId) {
		log.info("validate sign _GET");
		try {
			if (!getRequestValues(request, venderId)) {
				this.getResponse().getWriter().write("error");
				return;
			}

			String[] params = new String[] { token, timestamp, nonce };
			params = this.sortStrings(params);
			if (validateSign(params, signature)) {
				this.getResponse().getWriter().write(echostr);
				return;
			} else {
				//String accessToken = getAccessToken("wx23be1b9cbc0f1324", "6e53fa196c53c5af4d64c6477b3f92d4");
				this.getResponse().getWriter().write("");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "receiveMsg/{shopNum}/{venderId}", method = RequestMethod.POST)
	public void receiveMsgPost(HttpServletRequest request, HttpServletResponse response, @PathVariable String shopNum,
			@PathVariable String venderId) {
		log.info("validate sign _POST");
		try {
			response.setCharacterEncoding("UTF-8");
			boolean flag = this.getRequestValues(request, venderId);
			if (!flag) {
				this.getResponse().getWriter().write("error");
				return;
			}
			String[] params = new String[] { token, timestamp, nonce };
			params = this.sortStrings(params);
			log.info(Arrays.toString(params));
			if (validateSign(params, signature)) {
				String xmlContent = this.getPostData(request);
				log.info("received_xml>>>>>>>>>>>"+xmlContent);
				WeixinDataConvert<ReceivedMessage> convert = new WeixinDataConvert<ReceivedMessage>();
				ReceivedMessage receivedMsg = new ReceivedMessage();
				receivedMsg = convert.ConvertXmlToObject(xmlContent, receivedMsg);
				if(receivedMsg != null) receivedMsg.setVenderId(venderId);
				SendedMessage sendMsg = keywordsManager.getReplayContent(receivedMsg, shopNum);
				if(sendMsg == null){
					this.getResponse().getWriter().write("");
					return;	
				}
				WeixinDataConvert<SendedMessage> sendConvert = new WeixinDataConvert<SendedMessage>();
				String returnXml = sendConvert.ConvertObjectToXml(sendMsg);
				log.info(returnXml);
				this.getResponse().getWriter().write(returnXml);
				return;

			} else {
				this.getResponse().getWriter().write("");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean getRequestValues(HttpServletRequest request, String shopId) {
		signature = request.getParameter("signature");
		timestamp = request.getParameter("timestamp");
		nonce = request.getParameter("nonce");
		echostr = request.getParameter("echostr");
		//改为查询数据库
		token = ReloadableConfig.getProperty("weixin_token_"+shopId, "UDW5pqTDYGmaqhOL0gw");
		if (signature == null || timestamp == null || nonce == null || token == null)
			return false;
		return true;
	}

	public String getPostData(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		String line = null;
		StringBuffer postData = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			postData.append(line);
		}
		return postData.toString();
	}

	private static boolean validateSign(String[] strArr, String sign) {
		StringBuffer params = new StringBuffer();
		for(String s : strArr){
			params.append(s);
		}
		String destSign = SHAUtil.SHA(params.toString());
		if (sign.equals(destSign))
			return true;
		return false;
	}

	
	private String getAccessToken(String appId, String secret) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credential");
		params.put("appid", appId);
		params.put("secret", secret);
		String url = ReloadableConfig.getProperty("weixin_access_token_url", "https://api.weixin.qq.com/cgi-bin/token");
		return WebUtils.doGet(url, params);
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public IKeywordsManager getKeywordsManager() {
		return keywordsManager;
	}

	public void setKeywordsManager(IKeywordsManager keywordsManager) {
		this.keywordsManager = keywordsManager;
	}

}

package com.yao.model.weixin.shop;

import java.util.Date;

public class WeixinShop {
	private Integer id;
	//΢��token ˫��������֤����
	private String token;
	//32Ϊuuid
	private String venderId;
	//����΢�ŵ�url
	private String receiveUrl;
	private Date inputeTime;
	//΢��appId
	private String appId;
	//΢��secret
	private String appSecret;
	//΢������ ���ں� �����
	private String weixinType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	public String getReceiveUrl() {
		return receiveUrl;
	}
	public void setReceiveUrl(String receiveUrl) {
		this.receiveUrl = receiveUrl;
	}
	public Date getInputeTime() {
		return inputeTime;
	}
	public void setInputeTime(Date inputeTime) {
		this.inputeTime = inputeTime;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getWeixinType() {
		return weixinType;
	}
	public void setWeixinType(String weixinType) {
		this.weixinType = weixinType;
	}
	
}

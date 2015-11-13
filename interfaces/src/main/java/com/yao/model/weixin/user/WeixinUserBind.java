package com.yao.model.weixin.user;

import java.util.Date;

public class WeixinUserBind {
	private Integer id;
	private String openId;
	private String userId;
	private int status = 1;
	private Date bindDate;
	private Integer shopId;
	private String venderId;
	private Date unbindDate;
	private String recOpenId;
	private String taobaoAccount;
	private String weixinAccount;
	
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Date getBindDate() {
		return bindDate;
	}
	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	public Date getUnbindDate() {
		return unbindDate;
	}
	public void setUnbindDate(Date unbindDate) {
		this.unbindDate = unbindDate;
	}
	public String getRecOpenId() {
		return recOpenId;
	}
	public void setRecOpenId(String recOpenId) {
		this.recOpenId = recOpenId;
	}
	public String getTaobaoAccount() {
		return taobaoAccount;
	}
	public void setTaobaoAccount(String taobaoAccount) {
		this.taobaoAccount = taobaoAccount;
	}
	public String getWeixinAccount() {
		return weixinAccount;
	}
	public void setWeixinAccount(String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}

}

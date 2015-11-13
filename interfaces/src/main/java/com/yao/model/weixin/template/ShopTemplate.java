package com.yao.model.weixin.template;

import java.util.Date;

public class ShopTemplate extends Template {
	public static final int NO_FILL = 0;
	//µÍ∆ÃId
	private Integer id;
	private  Date expire;
	private Integer shopId;
	private int templateStatus;
	private int ifFillTemplate;
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public int getTemplateStatus() {
		return templateStatus;
	}
	public void setTemplateStatus(int templateStatus) {
		this.templateStatus = templateStatus;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getIfFillTemplate() {
		return ifFillTemplate;
	}
	public void setIfFillTemplate(int ifFillTemplate) {
		this.ifFillTemplate = ifFillTemplate;
	}
	
}

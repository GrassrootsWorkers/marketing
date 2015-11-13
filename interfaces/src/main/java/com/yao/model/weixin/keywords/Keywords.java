package com.yao.model.weixin.keywords;

import java.util.Date;

public class Keywords {
	public static final int default_keyword_id= 1;
	public static final String all_match = "ALL";
	public static final String fuzzy_match = "FUZZY";
	public static final String all_replay="ALL";
	public static final String random_replay="RANDOM";
	public static final int disable = 0;
	public static final int usable = 1;
	
	private Integer id;
	//回复的别名
	private String showName;
	//关键词
	private String content;
	//匹配类型 ALL:完全匹配（默认）  FUZZY:模糊匹配
	private String type = "ALL";
	/**
	 * 关键词所属分类 为了分类展示 
	 * order : 订单
	 * good:商品
	 * promotion:促销
	 * Logistics:物流
	 * advistory：咨询（默认）
	*/
	private String category = "advistory";
	private Integer shopId;
	//回复类型  关键词回复  关注回复  自动回复 
	private String replyType;
	private Integer templateId;
	/*
	 * ALL:全部回复
	 *RANDOM:随机发送一条
	 */
	private String replyMethod;
	private String msgType;
	private int status = 1;
	private String inputer;
	private Date inputeDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getInputer() {
		return inputer;
	}
	public void setInputer(String inputer) {
		this.inputer = inputer;
	}
	public Date getInputeDate() {
		return inputeDate;
	}
	public void setInputeDate(Date inputeDate) {
		this.inputeDate = inputeDate;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getReplyType() {
		return replyType;
	}
	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}
	public String getReplyMethod() {
		return replyMethod;
	}
	public void setReplyMethod(String replyMethod) {
		this.replyMethod = replyMethod;
	}

}

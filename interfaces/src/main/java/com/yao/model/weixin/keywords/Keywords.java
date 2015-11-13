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
	//�ظ��ı���
	private String showName;
	//�ؼ���
	private String content;
	//ƥ������ ALL:��ȫƥ�䣨Ĭ�ϣ�  FUZZY:ģ��ƥ��
	private String type = "ALL";
	/**
	 * �ؼ����������� Ϊ�˷���չʾ 
	 * order : ����
	 * good:��Ʒ
	 * promotion:����
	 * Logistics:����
	 * advistory����ѯ��Ĭ�ϣ�
	*/
	private String category = "advistory";
	private Integer shopId;
	//�ظ�����  �ؼ��ʻظ�  ��ע�ظ�  �Զ��ظ� 
	private String replyType;
	private Integer templateId;
	/*
	 * ALL:ȫ���ظ�
	 *RANDOM:�������һ��
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

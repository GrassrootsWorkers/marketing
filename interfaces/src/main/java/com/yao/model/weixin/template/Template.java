package com.yao.model.weixin.template;

import java.util.Date;

public class Template {
	private Integer templateId;
	private String templateContent;
	private String inputer;
	private Date inputeDate;
	/**
	 * text �ı�
		image:ͼƬ��Ϣ
		voice:����
		video ����Ƶ
		music �� ����
		news��ͼ��
		order:����
		item:��Ʒ
	 */
	private String templateType;
	private double salePrice;
	
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
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
	
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	
}

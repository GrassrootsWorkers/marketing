package com.yao.model.weixin.coupon;

import java.util.Date;

public class WeixinCouponInfo {
	private Integer id;
	//�Ż�ȯ���α��
	private String batchCode;
	//�Ż�ȯչʾ��
	private String showName;
	//�Ż�ȯԤ������
	private int freezeQuantity;
	//�Ѿ���ȡ���Ż�ȯ��
	private int usedCount;
	private Date inputeTime;
	private String inputer;
	//1������ Ĭ��ֵ  0��ͣ��
	private int status = 1;
	//����Id
	private Integer shopId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public int getFreezeQuantity() {
		return freezeQuantity;
	}
	public void setFreezeQuantity(int freezeQuantity) {
		this.freezeQuantity = freezeQuantity;
	}
	public int getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}
	public Date getInputeTime() {
		return inputeTime;
	}
	public void setInputeTime(Date inputeTime) {
		this.inputeTime = inputeTime;
	}
	public String getInputer() {
		return inputer;
	}
	public void setInputer(String inputer) {
		this.inputer = inputer;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	

}

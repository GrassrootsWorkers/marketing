package com.yao.model.weixin.coupon;

import java.util.Date;

public class WeixinCouponInfo {
	private Integer id;
	//优惠券批次编号
	private String batchCode;
	//优惠券展示名
	private String showName;
	//优惠券预留个数
	private int freezeQuantity;
	//已经领取的优惠券数
	private int usedCount;
	private Date inputeTime;
	private String inputer;
	//1：启用 默认值  0：停用
	private int status = 1;
	//店铺Id
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

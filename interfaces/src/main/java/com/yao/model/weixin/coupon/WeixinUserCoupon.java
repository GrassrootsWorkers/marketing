package com.yao.model.weixin.coupon;

import java.util.Date;

public class WeixinUserCoupon extends WeixinCouponInfo{
	private String openId;
	private Date getTime;
	private String userId;
	//优惠券发放间隔天数
	private int sendTime;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getGetTime() {
		return getTime;
	}
	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}
	public int getSendTime() {
		return sendTime;
	}
	public void setSendTime(int sendTime) {
		this.sendTime = sendTime;
	}

		public String getUserId() {
				return userId;
		}

		public void setUserId(String userId) {
				this.userId = userId;
		}
}

package com.yao.marketing.business.impl.weixin.task;

import com.yao.coupon.business.interfaces.ICouponRemoteManager;

public class CouponTask implements Runnable{
	private String userId;
	private String batchCode;
	private ICouponRemoteManager couponRemoteManager;
	public CouponTask(String userId, String batchCode,ICouponRemoteManager couponRemoteManager){
		this.userId = userId;
		this.batchCode = batchCode;
		this.couponRemoteManager = couponRemoteManager;
	}
	@Override
	public void run() {
		String result = couponRemoteManager.distributeCoupon(userId, batchCode,true);
		System.out.println(">>>>>>>>>>>>>>>>>"+result);
	}

}

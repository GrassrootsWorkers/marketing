package com.yao.marketing.interfaces.weixin.coupon;

import java.util.List;
import java.util.Map;

import com.yao.model.weixin.coupon.WeixinCouponInfo;
import com.yao.model.weixin.coupon.WeixinUserCoupon;

public interface IWeixinUserBindCouponManager {
	
	/**
	 * 保存要发放的优惠券信息
	 * @param couponInfo
	 * @return
	 */
	public int save(WeixinCouponInfo couponInfo);
	
	/**
	 * 发放一张优惠券到用户中
	 * @param coupon
	 * @return
	 */
	public int bindCouponToUser(WeixinUserCoupon coupon);
	
	/**
	 * 查询微信用户未绑定的优惠券批次
	 * @param openId
	 * @return
	 */
	public Map<String,Integer> queryUnBindCouponByOpenId(String openId, String userId);
	/**
	 * 查询用户领取优惠券情况
	 * @param coupon
	 * @return
	 */
	public List<WeixinCouponInfo> queryCouponInfos(WeixinCouponInfo coupon);
	

}

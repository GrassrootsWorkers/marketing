package com.yao.marketing.persistences.interfaces.weixin.coupon;

import java.util.List;

import com.yao.model.weixin.coupon.WeixinCouponInfo;
import com.yao.model.weixin.coupon.WeixinUserCoupon;

public interface IWeixinCouponDao {
	/**
	 * 用户领取优惠券
	 * @param coupon
	 * @return
	 */
	public int insert(WeixinUserCoupon coupon);
	
	/**
	 * 根据用户Id获取该用户已经领取的优惠券
	 * @param userCoupon
	 * @return
	 */
	public List<WeixinUserCoupon> queryBindedCoupons(WeixinCouponInfo userCoupon);
	
	/**
	 * 用户领取优惠券后扣减掉优惠券
	 * @param batchCode
	 * @return
	 */
	public int reduceCouponCount(String batchCode);
	/**
	 * 添加要发送的优惠券信息
	 * @param coupon
	 * @return
	 */
	public int insertCouponInfo(WeixinCouponInfo coupon);
	
	/**
	 * 查询用户领取优惠券情况
	 * @param coupon
	 * @return
	 */
	public List<WeixinCouponInfo> queryCouponInfos(WeixinCouponInfo coupon);
	
	

}

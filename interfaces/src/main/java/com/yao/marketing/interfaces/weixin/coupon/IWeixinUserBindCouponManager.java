package com.yao.marketing.interfaces.weixin.coupon;

import java.util.List;
import java.util.Map;

import com.yao.model.weixin.coupon.WeixinCouponInfo;
import com.yao.model.weixin.coupon.WeixinUserCoupon;

public interface IWeixinUserBindCouponManager {
	
	/**
	 * ����Ҫ���ŵ��Ż�ȯ��Ϣ
	 * @param couponInfo
	 * @return
	 */
	public int save(WeixinCouponInfo couponInfo);
	
	/**
	 * ����һ���Ż�ȯ���û���
	 * @param coupon
	 * @return
	 */
	public int bindCouponToUser(WeixinUserCoupon coupon);
	
	/**
	 * ��ѯ΢���û�δ�󶨵��Ż�ȯ����
	 * @param openId
	 * @return
	 */
	public Map<String,Integer> queryUnBindCouponByOpenId(String openId, String userId);
	/**
	 * ��ѯ�û���ȡ�Ż�ȯ���
	 * @param coupon
	 * @return
	 */
	public List<WeixinCouponInfo> queryCouponInfos(WeixinCouponInfo coupon);
	

}

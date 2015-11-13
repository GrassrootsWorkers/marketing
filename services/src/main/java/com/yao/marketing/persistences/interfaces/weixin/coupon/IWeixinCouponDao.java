package com.yao.marketing.persistences.interfaces.weixin.coupon;

import java.util.List;

import com.yao.model.weixin.coupon.WeixinCouponInfo;
import com.yao.model.weixin.coupon.WeixinUserCoupon;

public interface IWeixinCouponDao {
	/**
	 * �û���ȡ�Ż�ȯ
	 * @param coupon
	 * @return
	 */
	public int insert(WeixinUserCoupon coupon);
	
	/**
	 * �����û�Id��ȡ���û��Ѿ���ȡ���Ż�ȯ
	 * @param userCoupon
	 * @return
	 */
	public List<WeixinUserCoupon> queryBindedCoupons(WeixinCouponInfo userCoupon);
	
	/**
	 * �û���ȡ�Ż�ȯ��ۼ����Ż�ȯ
	 * @param batchCode
	 * @return
	 */
	public int reduceCouponCount(String batchCode);
	/**
	 * ���Ҫ���͵��Ż�ȯ��Ϣ
	 * @param coupon
	 * @return
	 */
	public int insertCouponInfo(WeixinCouponInfo coupon);
	
	/**
	 * ��ѯ�û���ȡ�Ż�ȯ���
	 * @param coupon
	 * @return
	 */
	public List<WeixinCouponInfo> queryCouponInfos(WeixinCouponInfo coupon);
	
	

}

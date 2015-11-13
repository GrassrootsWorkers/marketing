package com.yao.marketing.business.impl.weixin.coupon;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yao.marketing.config.ReloadableConfig;
import com.yao.marketing.interfaces.weixin.coupon.IWeixinUserBindCouponManager;
import com.yao.marketing.persistences.interfaces.weixin.coupon.IWeixinCouponDao;
import com.yao.model.weixin.coupon.WeixinCouponInfo;
import com.yao.model.weixin.coupon.WeixinUserCoupon;

public class WeixinUserBindCouponManagerImpl implements IWeixinUserBindCouponManager {

	@Autowired
	IWeixinCouponDao weixinUserCouponDao;
	@Override
	public int save(WeixinCouponInfo couponInfo) {
		return weixinUserCouponDao.insertCouponInfo(couponInfo);
	}

	@Override
	public int bindCouponToUser(WeixinUserCoupon coupon) {
		coupon.setGetTime(new Date());
		weixinUserCouponDao.insert(coupon);
		int id = coupon.getId();
		if( id > 0){
			weixinUserCouponDao.reduceCouponCount(coupon.getBatchCode());
		}
		return id;
	}

	@Override
	public Map<String, Integer> queryUnBindCouponByOpenId(String openId, String userId) {
		WeixinUserCoupon userCoupon = new WeixinUserCoupon();
		userCoupon.setOpenId(openId);
		userCoupon.setStatus(1);
		int time = ReloadableConfig.getInt("coupon_send_time", 1);
		userCoupon.setSendTime(time);
		userCoupon.setFreezeQuantity(1);
		userCoupon.setUserId(userId);
		List<WeixinUserCoupon> bindedCouponList = weixinUserCouponDao.queryBindedCoupons(userCoupon);
		userCoupon = new WeixinUserCoupon();
		userCoupon.setStatus(1);
		List<WeixinCouponInfo> couponInfo =  queryCouponInfos(userCoupon);
		 
		return getUnbindBatchCode(bindedCouponList, couponInfo);
	}
	private Map<String,Integer> getUnbindBatchCode(List<WeixinUserCoupon> coupons, List<WeixinCouponInfo> infos) {
		Map<String,Integer> unbindedCoupon = new HashMap<String,Integer>();
		for(WeixinCouponInfo info : infos ) {
				unbindedCoupon.put(info.getBatchCode(), info.getFreezeQuantity());
		}
		for(WeixinUserCoupon coupon : coupons){
			String key = coupon.getBatchCode();
			if(unbindedCoupon.get(key)!=null){
				unbindedCoupon.remove(key);
			}
		}
		
		return unbindedCoupon;
	}
	@Override
	public List<WeixinCouponInfo> queryCouponInfos(WeixinCouponInfo coupon) {
		
		return weixinUserCouponDao.queryCouponInfos(coupon);
	}
}

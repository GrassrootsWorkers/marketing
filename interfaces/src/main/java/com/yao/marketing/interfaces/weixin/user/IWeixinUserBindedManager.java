package com.yao.marketing.interfaces.weixin.user;

import java.util.List;

import com.yao.model.weixin.user.WeixinUserBind;

public interface IWeixinUserBindedManager {
	
	public int insert(WeixinUserBind weixinUser);

	public List<WeixinUserBind> query(WeixinUserBind weixinUser);
	
	int delete(String userName,String openId, Integer shopId);
	
	public int update(WeixinUserBind weixinUser);

	public int statistic(WeixinUserBind weiXinUser);
	
	/**
	 * 验证用户是否绑定过
	 * @param openId
	 * @return
	 */
	public boolean validateIfBinded(String openId);
	
	/**
	 * 得到微信用户绑定的官网用户--发放优惠券用
	 * @param openId
	 * @return
	 */
	public String getBindedOfficialUsereId(String openId);
	
}

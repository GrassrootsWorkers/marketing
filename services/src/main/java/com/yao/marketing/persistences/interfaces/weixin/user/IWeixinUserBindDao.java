package com.yao.marketing.persistences.interfaces.weixin.user;

import java.util.List;

import com.yao.model.weixin.user.WeixinUserBind;

public interface IWeixinUserBindDao {
	
	int insert(WeixinUserBind weixinUser);

	List<WeixinUserBind> query(WeixinUserBind weixinUser);

	int update(WeixinUserBind weixinUser);
	
	int delete(WeixinUserBind weixinUser);

	int statistic(WeixinUserBind weiXinUser);
	
}

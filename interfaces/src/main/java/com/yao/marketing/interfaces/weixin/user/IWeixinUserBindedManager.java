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
	 * ��֤�û��Ƿ�󶨹�
	 * @param openId
	 * @return
	 */
	public boolean validateIfBinded(String openId);
	
	/**
	 * �õ�΢���û��󶨵Ĺ����û�--�����Ż�ȯ��
	 * @param openId
	 * @return
	 */
	public String getBindedOfficialUsereId(String openId);
	
}

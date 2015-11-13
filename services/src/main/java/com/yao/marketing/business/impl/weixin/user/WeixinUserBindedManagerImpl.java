package com.yao.marketing.business.impl.weixin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yao.marketing.interfaces.weixin.user.IWeixinUserBindedManager;
import com.yao.marketing.persistences.interfaces.weixin.user.IWeixinUserBindDao;
import com.yao.model.weixin.user.WeixinUserBind;

public class WeixinUserBindedManagerImpl implements IWeixinUserBindedManager{

	@Autowired
	private IWeixinUserBindDao bindWeixinUserDao; 
	@Override
	public int insert(WeixinUserBind weixinUser) {
		 try {
			bindWeixinUserDao.insert(weixinUser);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<WeixinUserBind> query(WeixinUserBind weixinUser) {
		List<WeixinUserBind> userBinds = bindWeixinUserDao.query(weixinUser);
		return userBinds;
	}

	@Override
	public int update(WeixinUserBind weixinUser) {
		return bindWeixinUserDao.update(weixinUser);
	}
	
	@Override
	public int delete(String userName, String openId ,Integer shopId) {
		if(openId == null) return 0;
		WeixinUserBind weixinUser = new WeixinUserBind();
		weixinUser.setShopId(shopId);
		weixinUser.setOpenId(openId);
		weixinUser.setUserId(userName);
		return bindWeixinUserDao.delete(weixinUser);
	}
	@Override
	public int statistic(WeixinUserBind weiXinUser) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public boolean validateIfBinded(String openId) {
		WeixinUserBind weixinUser = new WeixinUserBind();
		weixinUser.setOpenId(openId);
		weixinUser.setStatus(1);
		if(query(weixinUser).size() >0) return true;
		return false;
	}
	public IWeixinUserBindDao getBindWeixinUserDao() {
		return bindWeixinUserDao;
	}

	public void setBindWeixinUserDao(IWeixinUserBindDao bindWeixinUserDao) {
		this.bindWeixinUserDao = bindWeixinUserDao;
	}

	@Override
	public String getBindedOfficialUsereId(String openId) {
		WeixinUserBind weixinUser = new WeixinUserBind();
		weixinUser.setOpenId(openId);
		weixinUser.setStatus(1);
		List<WeixinUserBind> userBinds = query(weixinUser);
		if(userBinds.size() >0) return userBinds.get(0).getUserId();
		return null;
	}

	

}

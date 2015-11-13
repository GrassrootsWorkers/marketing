package com.yao.marketing.business.impl.weixin.keywords;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;

import com.yao.coupon.business.interfaces.ICouponRemoteManager;
import com.yao.marketing.business.impl.weixin.task.CouponTask;
import com.yao.marketing.config.ReloadableConfig;
import com.yao.marketing.interfaces.weixin.coupon.IWeixinUserBindCouponManager;
import com.yao.marketing.interfaces.weixin.keywords.IKeywordsManager;
import com.yao.marketing.interfaces.weixin.keywords.IReplyMessage;
import com.yao.marketing.interfaces.weixin.user.IWeixinUserBindedManager;
import com.yao.marketing.persistences.interfaces.weixin.keywords.IKeywordsDao;
import com.yao.marketing.string.StringUtil;
import com.yao.model.weixin.constant.EventType;
import com.yao.model.weixin.constant.MessageType;
import com.yao.model.weixin.coupon.WeixinUserCoupon;
import com.yao.model.weixin.keywords.Keywords;
import com.yao.model.weixin.receive.ReceivedMessage;
import com.yao.model.weixin.send.SendedMessage;
import com.yao.model.weixin.template.ShopTemplate;

public class KeywordsManagerImpl implements IKeywordsManager {

		@Autowired
		IKeywordsDao keywordsDao;
		@Autowired
		IWeixinUserBindedManager weixinbindedManager;
		@Autowired
		IWeixinUserBindCouponManager weixinCouponManager;
		@Autowired
		ThreadPoolExecutor couponSendThreadPool;
		@Autowired
		private ICouponRemoteManager couponRemoteManager;

		@Override
		public List<ShopTemplate> queryTemplate(int keywordsId, int shopId) {
				Map<String, Integer> keywordIdAndShopId = new HashMap<String, Integer>();
				keywordIdAndShopId.put("shopId", shopId);
				keywordIdAndShopId.put("keywordId", keywordsId);
				return keywordsDao.queryTemplate(keywordIdAndShopId);
		}


		@Override
		public SendedMessage getReplayContent(ReceivedMessage msg, String shopId) {

				String content = getContent(msg);
				//发放优惠券
				String successBindCoupon = ReloadableConfig.getProperty("success_get_coupon", "领券成功");
				if (successBindCoupon.equals(content)) {
						String officialUserId = weixinbindedManager.getBindedOfficialUsereId(msg.getFromUserName());
						Map<String, Integer> unbindCouponMap = weixinCouponManager.queryUnBindCouponByOpenId(msg.getFromUserName(),officialUserId);

						Set<String> keys = unbindCouponMap.keySet();
						for (String key : keys) {
								if (insertUserBindCoupon(msg.getFromUserName(), key, shopId,officialUserId)) {
										CouponTask task = new CouponTask(officialUserId, key, couponRemoteManager);
										couponSendThreadPool.execute(task);
								}
						}
				}
				Keywords keywords = getKeywordsIdContent(content, Integer.parseInt(shopId));

				List<ShopTemplate> templates = queryTemplate(keywords.getId(), Integer.parseInt(shopId));
				if (templates.size() <= 0) return null;
				List<SendedMessage> sendedMsgList = getSendedMsgByTemplates(templates, msg, keywords.getReplyMethod());
				return getOptimalSendedMsg(sendedMsgList);
		}

		private String getContent(ReceivedMessage msg) {

				String couponKey = ReloadableConfig.getProperty("coupon_keyword", "优惠券");
				String bindUserKey = ReloadableConfig.getProperty("bind_user_keyword", "绑定");
				String msgType = msg.getMsgType();
				String content = msg.getContent();

				if (MessageType.event.toString().equals(msgType)) {
						if (EventType.VIEW.toString().equals(msg.getEvent())) {
								return null;
						}
						if (EventType.CLICK.toString().equals(msg.getEvent())) {
								content = getMenusKeyMap().get(msg.getEventKey());
						} else {
								content = msg.getEvent();
						}

				}

				if (!StringUtil.areNotEmpty(content)) {
						content = ReloadableConfig.getProperty("default_key", "欢迎");
						return content;
				}

				String validateContent = ReloadableConfig.getProperty("need_validate_content", "解绑_优惠券_绑定_订单");
				boolean flag = validateContent.contains(content);
				if (flag) {
						boolean ifValidated = weixinbindedManager.validateIfBinded(msg.getFromUserName());
						if (ifValidated) {
								String username = weixinbindedManager.getBindedOfficialUsereId(msg.getFromUserName());
								msg.setUsername(username);
								if (bindUserKey.equals(content)) {
										content = ReloadableConfig.getProperty("alreadybind_user_keyword", "已绑定");
								}
								if (couponKey.equals(content)) {
										Map<String, Integer> unbindCouponMap = weixinCouponManager.queryUnBindCouponByOpenId(msg.getFromUserName(),username);
										if (unbindCouponMap.size() <= 0) {
												content = ReloadableConfig.getProperty("already_get_coupon", "已领券");
										} else {
												content = ReloadableConfig.getProperty("success_get_coupon", "领券成功");
										}
								}
						} else {
								content = ReloadableConfig.getProperty("bind_user_keyword", "绑定");
						}
				}
				return content;
		}

		private Map<String, String> getMenusKeyMap() {
				String keyStr = ReloadableConfig.getProperty("menus_keys", "1elfft3xm:绑定;xt2b4ing:解绑;55714mzg:优惠券;123fv15b9:APP;f70gp238:客服;6bp9gh3n:订单");
				Map<String, String> map = new HashMap<String, String>();
				String[] keys = keyStr.split(";");
				for (String key : keys) {
						String[] k = key.split(":");
						map.put(k[0], k[1]);
				}
				return map;
		}


		private boolean insertUserBindCoupon(String openId, String batchCode, String shopId, String userId) {
				WeixinUserCoupon coupon = new WeixinUserCoupon();
				coupon.setOpenId(openId);
				coupon.setBatchCode(batchCode);
				coupon.setShopId(Integer.parseInt(shopId));
				coupon.setGetTime(new Date());
				coupon.setUserId(userId);
				int id = weixinCouponManager.bindCouponToUser(coupon);
				if (id > 0) return true;
				return false;
		}

		private Keywords getKeywordsIdContent(String content, Integer shopId) {
				Keywords keyword = new Keywords();
				if (content == null || "".equals(content)) {
						keyword.setId(Keywords.default_keyword_id);
						return keyword;
				}
				keyword.setContent(content);
				keyword.setShopId(shopId);
				keyword.setType(Keywords.all_match);
				keyword.setStatus(Keywords.usable);
				List<Keywords> allKeywords = queryKeywords(keyword);
				if (allKeywords.size() > 0) return allKeywords.get(0);
				keyword.setType(Keywords.fuzzy_match);
				List<Keywords> fuzzyKeywords = queryKeywords(keyword);
				if (fuzzyKeywords.size() > 0) return fuzzyKeywords.get(0);
				keyword.setId(Keywords.default_keyword_id);
				return keyword;

		}

		public List<SendedMessage> getSendedMsgByTemplates(List<ShopTemplate> templates, ReceivedMessage msg, String replayMethod) {
				List<SendedMessage> sendMsgList = new ArrayList<SendedMessage>();
				if (Keywords.random_replay.equals(replayMethod)) {
						ShopTemplate template = templates.get(0);
						IReplyMessage replay = ReplyWeixinMessageFactory.getInstance(template.getTemplateType());
						if (replay == null) return sendMsgList;
						sendMsgList.add(replay.replay(msg, template));
						return sendMsgList;
				}
				for (ShopTemplate t : templates) {
						IReplyMessage replay = ReplyWeixinMessageFactory.getInstance(t.getTemplateType());
						sendMsgList.add(replay.replay(msg, t));
				}
				return sendMsgList;
		}

		public SendedMessage getOptimalSendedMsg(List<SendedMessage> sendMsgList) {
				if (sendMsgList.size() <= 0) return null;
				return sendMsgList.get(0);
		}

		@Override
		public int insertKeywords(Keywords keyword) {
				return keywordsDao.insert(keyword);
		}

		@Override
		public int deleteKeywords(Keywords keyword) {
				return 0;
		}

		@Override
		public List<Keywords> queryKeywords(Keywords keywords) {
				return keywordsDao.query(keywords);
		}

		public IKeywordsDao getKeywordsDao() {
				return keywordsDao;
		}

		public void setKeywordsDao(IKeywordsDao keywordsDao) {
				this.keywordsDao = keywordsDao;
		}

		public IWeixinUserBindedManager getWeixinbindedManager() {
				return weixinbindedManager;
		}

		public void setWeixinbindedManager(IWeixinUserBindedManager weixinbindedManager) {
				this.weixinbindedManager = weixinbindedManager;
		}

		public IWeixinUserBindCouponManager getWeixinCouponManager() {
				return weixinCouponManager;
		}

		public void setWeixinCouponManager(IWeixinUserBindCouponManager weixinCouponManager) {
				this.weixinCouponManager = weixinCouponManager;
		}

		public ThreadPoolExecutor getCouponSendThreadPool() {
				return couponSendThreadPool;
		}

		public void setCouponSendThreadPool(ThreadPoolExecutor couponSendThreadPool) {
				this.couponSendThreadPool = couponSendThreadPool;
		}

		public ICouponRemoteManager getCouponRemoteManager() {
				return couponRemoteManager;
		}

		public void setCouponRemoteManager(ICouponRemoteManager couponRemoteManager) {
				this.couponRemoteManager = couponRemoteManager;
		}


}

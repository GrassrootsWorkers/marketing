package com.yao.marketing.interfaces.weixin.keywords;

import com.yao.model.weixin.receive.ReceivedMessage;
import com.yao.model.weixin.send.SendedMessage;
import com.yao.model.weixin.template.ShopTemplate;

public interface IReplyMessage {
	/**
	 * 接收信息后返回要返回的信息
	 * @param message
	 * @return
	 */
	public SendedMessage replay(ReceivedMessage message, ShopTemplate template);
}

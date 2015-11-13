package com.yao.marketing.interfaces.weixin.keywords;

import com.yao.model.weixin.receive.ReceivedMessage;
import com.yao.model.weixin.send.SendedMessage;
import com.yao.model.weixin.template.ShopTemplate;

public interface IReplyMessage {
	/**
	 * ������Ϣ�󷵻�Ҫ���ص���Ϣ
	 * @param message
	 * @return
	 */
	public SendedMessage replay(ReceivedMessage message, ShopTemplate template);
}

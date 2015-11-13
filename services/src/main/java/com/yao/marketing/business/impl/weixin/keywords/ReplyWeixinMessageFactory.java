package com.yao.marketing.business.impl.weixin.keywords;

import com.yao.marketing.interfaces.weixin.keywords.IReplyMessage;
import com.yao.model.weixin.constant.MessageType;

public class ReplyWeixinMessageFactory {
	
	public static IReplyMessage getInstance(String name) {
		if(MessageType.text.toString().equals(name)) {
			return new ReplyWeixinTextMessage();
		}
		return new ReplyWeixinTextMessage();
	}

}

package com.yao.marketing.business.impl.weixin.keywords;

import java.net.URL;

import com.yao.marketing.string.StringUtil;
import org.apache.velocity.VelocityContext;

import com.yao.marketing.business.impl.weixin.velocity.VelocityTool;
import com.yao.marketing.interfaces.weixin.keywords.IReplyMessage;
import com.yao.model.convert.WeixinDataConvert;
import com.yao.model.weixin.receive.ReceivedMessage;
import com.yao.model.weixin.send.SendedMessage;
import com.yao.model.weixin.template.ShopTemplate;

public class ReplyWeixinTextMessage implements IReplyMessage {

		@Override
		public SendedMessage replay(ReceivedMessage message, ShopTemplate template) {
				WeixinDataConvert<SendedMessage> convert = new WeixinDataConvert<SendedMessage>();
				String content = template.getTemplateContent();
				SendedMessage sendedMsg = new SendedMessage();
				sendedMsg = convert.ConvertXmlToObject(content, sendedMsg);
				sendedMsg.setFromUserName(message.getToUserName());
				sendedMsg.setToUserName(message.getFromUserName());
				sendedMsg.setCreateTime(System.currentTimeMillis());
				if (template.getIfFillTemplate() == ShopTemplate.NO_FILL) {
						return sendedMsg;
				}
				content = sendedMsg.getContent();
				try {
						VelocityContext context = new VelocityContext();
						context.put("shopId", template.getShopId());
						context.put("openId", message.getFromUserName());
						context.put("venderId", message.getVenderId());
						String userName = message.getUsername();
						if (StringUtil.areNotEmpty(userName)) {
								userName = userName.substring(0, 3) + "***" + userName.substring(userName.length() - 2, userName.length());
						} else {
								userName = "¿Í¹Ù";
						}
						context.put("username", userName);
						URL resource = getClass().getResource("/");
						String templateStr = VelocityTool.getTemplateContent(content, context, resource.getPath());
						sendedMsg.setContent(templateStr);
				} catch (Exception e) {
						e.printStackTrace();
						sendedMsg.setContent("ÏµÍ³·±Ã¦£¡");
				}
				return sendedMsg;

		}

}

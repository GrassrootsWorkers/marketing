package com.yao.marketing.interfaces.weixin.keywords;

import java.util.List;

import com.yao.model.weixin.keywords.Keywords;
import com.yao.model.weixin.receive.ReceivedMessage;
import com.yao.model.weixin.send.SendedMessage;
import com.yao.model.weixin.template.ShopTemplate;

public interface IKeywordsManager {
	/**
	 * @param keywords
	 */
	List<Keywords> queryKeywords(Keywords keywords);

	/**
	 * @param keywordsId
	 */
	List<ShopTemplate> queryTemplate(int keywordsId, int shopId);

	/**
	 * @param keyword
	 */
	int insertKeywords(Keywords keyword);

	/**
	 * @param keyword
	 */
	int deleteKeywords(Keywords keyword);
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	public SendedMessage getReplayContent(ReceivedMessage msg, String shopId);
	
}

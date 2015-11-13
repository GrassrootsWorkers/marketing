package com.yao.marketing.persistences.interfaces.weixin.keywords;

import java.util.List;
import java.util.Map;

import com.yao.model.weixin.keywords.Keywords;
import com.yao.model.weixin.template.ShopTemplate;

public interface IKeywordsDao {
	/**
	 * @param keyword
	 */
	int insert(Keywords keyword);

	/**
	 * ²éÑ¯Ä£°å
	 * @param keyword
	 * @return
	 */
	List<Keywords> query(Keywords keyword);

	/**
	 * @param keyword
	 */
	int update(Keywords keyword);

	/**
	 * @param keyword
	 */
	int delete(Keywords keyword);

	/**
	 * @param keywordId
	 */
	List<ShopTemplate> queryTemplate(Map<String,Integer> keywordIdAndShopId);

}
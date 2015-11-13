package com.yao.marketing.persistences.interfaces.weixin.template;

import java.util.List;

import com.yao.model.weixin.template.ShopTemplate;

public interface IShoptemplateDao {
	/**
	 * @param template
	 */
	int insert(ShopTemplate template);

	/**
	 * @param template
	 */
	List<ShopTemplate> query(ShopTemplate template);

}

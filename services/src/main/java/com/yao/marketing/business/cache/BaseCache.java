package com.yao.marketing.business.cache;

public interface BaseCache {
	/**
	 *保存key
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean insert(String key, String value);
	
	/**
	 * 插入一个存活expire的key
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public boolean insert(String key, String value, int expire);
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String query(String key);
	
}

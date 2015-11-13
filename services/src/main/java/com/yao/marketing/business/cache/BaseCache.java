package com.yao.marketing.business.cache;

public interface BaseCache {
	/**
	 *����key
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean insert(String key, String value);
	
	/**
	 * ����һ�����expire��key
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

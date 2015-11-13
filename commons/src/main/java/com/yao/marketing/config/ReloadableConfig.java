package com.yao.marketing.config;/*
 * 文件名： ReloadableConfig.java
 * 
 * 创建日期： 2010-5-22
 *
 * Cretead by <a href="mailto:songpp22@gmail.com">songpp</a>.
 *
 */


import javax.naming.ConfigurationException;
import java.io.IOException;
import java.util.List;

public class ReloadableConfig {
	
	private final static String APP_GROUP = "DEFAULT_GROUP";
	private final static String APP_NAME = "weixin";
	
	private ReloadableConfig(){}
	
	static{
		try{
			init();
		}catch(Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public synchronized static void init() throws IOException, ConfigurationException {
		try {
			DiamondConfig.setAppName(APP_NAME);
			DiamondConfig.setGroupName(APP_GROUP);
			DiamondConfig.init();
		} catch(Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static String getProperty(String key,String defaultValue)
	{
		return DiamondConfig.getProperty(key, defaultValue);
	}
	
	public static Integer getInt(String key,int defaultVal)
	{
		return DiamondConfig.getInt(key, defaultVal);
	}
	
	public static Double getDouble(String key,Double defaultVal){
		return DiamondConfig.getDouble(key, defaultVal);
	}

	public static List<Object> getList(String key)
	{
		return DiamondConfig.getList(key);
	}
	
	public static Object getProperty(String name){
		return DiamondConfig.getProperty(name);
	}

	public static String getUploadDir(){
		return DiamondConfig.getProperty("other_upload_dir", "");
	}

	public static String getAdvertisementPath() {
		return DiamondConfig.getProperty("advertisement.path", "");
	}
	
	public static void main(String[] args) {
		System.out.println(ReloadableConfig.getProperty("app_domain"));
	}
}

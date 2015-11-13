package com.yao.marketing.config;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;

import com.yao.marketing.string.StringUtil;


 
public class PassportConfig {
	private final static String APP_GROUP = "DEFAULT_GROUP";
	private final static String APP_NAME = "passport";
	static {
		try {
			init();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static int getConfigIntVal(String key) {
		try {
			return Integer.parseInt((String) DiamondConfig.getProperty(key));
		} catch (Exception e) {
			return 24 * 60 * 60;
		}
	}

	public synchronized static void init() throws IOException,
			ConfigurationException {
		try {
			System.out.println("init...");
			DiamondConfig.setAppName(APP_NAME);
			DiamondConfig.setGroupName(APP_GROUP);
			DiamondConfig.init();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	
    /**
     * 获取配置文件中的值
     */
//    public static int getConfigIntVal(String key){
//        try{
//            reLoad();
//            return Integer.parseInt((String)props.getProperty(key));
//        }catch(Exception e){
//            return 24*60*60;
//        }
//    }
    /**
     * 获取配置文件中的值
     */
    public static String getConfigVal(String key){
        try{
        	return (String) DiamondConfig.getProperty(key);
        }catch(Exception e){
            return null;
        }
    }
    public static String getProperty(String key,String defaultValue)
    {	
    	String result = (String) DiamondConfig.getProperty(key);
    	if(StringUtil.isNullorBlank(result)){
    		return defaultValue;
    	}
        return result;
    }
    public static void main(String[] args) {
		String result = getConfigVal("sms_href");
		System.out.println(result);
		
	}
}

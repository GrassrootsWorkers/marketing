package com.yao.marketing.config;


import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;
import com.yao.marketing.string.StringUtil;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;


public class DiamondConfig {

    private static final String CONFIG_PRE = "com.yao.common.config";
    private static String appName;
    private static String groupName;
    private static String encode = "GBK";
    private static PropertiesConfiguration prop;
    private static ReentrantLock lock = new ReentrantLock();
    private static DiamondManager manager;

    public DiamondConfig() {
    }

    public static void init() {
        appName = CONFIG_PRE + "." + appName;

        load();
    }

    public static void load() {
        if (!StringUtil.areNotEmpty(appName, groupName)) {
            throw new RuntimeException("必须设置 appName 和  groupName");
        }

        manager = new DefaultDiamondManager(groupName, appName, new ManagerListener() {

            public Executor getExecutor() {
                return null;
            }

            public void receiveConfigInfo(String configInfo) {
                addData(configInfo);
            }
        });

        addData(manager.getAvailableConfigureInfomation(10000));
    }

    /**
     * 全量更新
     *
     * @param config
     */
    private static void addData(String config) {
        if (StringUtil.isNullorBlank(config)) {
            return;
        }

        InputStream in = null;
        try {
            in = new ByteArrayInputStream(config.getBytes(encode));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        try {
            lock.lock();

            PropertiesConfiguration temp = new PropertiesConfiguration();
            temp.setEncoding(encode);
            temp.load(in);
            prop = temp;
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
            lock.unlock();
        }
    }

    public static String getProperty(String key, String defaultValue) {
        return prop.getString(key, defaultValue);
    }

    public static Integer getInt(String key, int defaultVal) {
        return prop.getInt(key, defaultVal);
    }

    public static Double getDouble(String key, Double defaultVal) {
        return prop.getDouble(key, defaultVal);
    }

    public static List<Object> getList(String key) {
        return prop.getList(key);
    }

    public static Object getProperty(String name) {
        return prop.getProperty(name);
    }

    public static Properties getProperties(String prefix) {
        return prop.getProperties(prefix);
    }

    public static void setAppName(String appName) {
        DiamondConfig.appName = appName;
    }

    public static void setGroupName(String groupName) {
        DiamondConfig.groupName = groupName;
    }

    public static void setEncode(String encode) {
        DiamondConfig.encode = encode;
    }

}
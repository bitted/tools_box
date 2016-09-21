package com.cyou.hithere.center.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Properties {
	private static PropertiesConfiguration config = null;
	private static final Logger LOG = LoggerFactory.getLogger(Properties.class);
	private static String file = null;
	public static void init(String path) {
		try {
			file = path;
			config = new PropertiesConfiguration(path);
		} catch (ConfigurationException e) {
			LOG.error("读取属性配置文件失败  path:{}", path, e);
		}
	}
	
	public static synchronized String getProperty(String name) {
		return config.getString(name);
	}
	
	public static synchronized void setProperty(String name, String value) {
		config.setProperty(name, value);
		try {
			config.save();
		} catch (ConfigurationException e) {
			LOG.error("写入属性配置文件失败  path:{}", file, e);
		}
	}
}

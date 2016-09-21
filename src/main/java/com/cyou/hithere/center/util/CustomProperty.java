package com.cyou.hithere.center.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 配置文件工具
 * @author yaoqingyuan
 *
 */
public class CustomProperty extends PropertyPlaceholderConfigurer {
	
	 private final static Map<String, Object> properties = new HashMap<String,Object>(); 
	 
	 @Override 
     protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, 
             Properties props) throws BeansException { 
		 
         super.processProperties(beanFactoryToProcess, props); 
         
         for (Object key : props.keySet()) { 
             String keyStr = key.toString(); 
             String value = props.getProperty(keyStr); 
             properties.put(keyStr, value); 
         }   
     } 
  
     public static Object getContextProperty(String key) { 
         return properties.get(key); 
     } 
}

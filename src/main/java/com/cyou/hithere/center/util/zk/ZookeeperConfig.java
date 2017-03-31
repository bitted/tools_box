package com.cyou.hithere.center.util.zk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * (获取zk配置)
 * 
 * @author litaijun
 * @create 2017年3月31日 下午4:58:50
 */
@Component(value = "zooKeeperConfig")
@PropertySource({"classpath:config-toolkit.properties","classpath:config.properties"})
public class ZookeeperConfig {
	private static final Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);

	@Autowired
	private Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public String getConfig(String path) {
		return env.getProperty(path);
	}
}

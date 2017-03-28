/**
 * 
 */
package com.cyou.hithere.center.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author litaijun
 *
 */
@Component
public class ConfigCenter {
	
	@Value("#{publicConfig}")
	private Map<String, Object> publicConfig;

	public Map<String, Object> getPublicConfig() {
		return publicConfig;
	}

	public void setPublicConfig(Map<String, Object> publicConfig) {
		this.publicConfig = publicConfig;
	}

}

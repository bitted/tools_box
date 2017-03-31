package com.cyou.hithere.center.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyou.hithere.center.config.ConfigCenter;
import com.cyou.hithere.center.util.zk.ZookeeperConfig;
import com.cyou.hithere.center.vo.BaseRet;

/**
 * 演示zk获取zk中统一配置属性
 * 
 * @author litaijun
 *
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String address = "zk.configs.address";
	private static final String rootNode = "zk.configs.rootNode";
	private static final String version = "zk.configs.version";
	@Autowired
	private ConfigCenter configCenter;
	@Autowired
	private ZookeeperConfig zooKeeperConfig;
	
	@RequestMapping(value = "query")
	@ResponseBody
	public String query(HttpServletRequest request, HttpServletResponse response) {
		BaseRet br = new BaseRet();
		Map<String, Object> map = configCenter.getPublicConfig();
		br.setData(map);
		return br.toString();
	}

	@RequestMapping(value = "print")
	@ResponseBody
	public String printZkConfig(HttpServletRequest request, HttpServletResponse response) {
		BaseRet br = new BaseRet();
		br.putData("address",zooKeeperConfig.getConfig(address));
		br.putData("rootNode",zooKeeperConfig.getConfig(rootNode));
		br.putData("version",zooKeeperConfig.getConfig(version));
		br.putData("iplist",zooKeeperConfig.getConfig("chat.mongoDBIP.list"));

		return br.toString();
	}

}

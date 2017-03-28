package com.cyou.hithere.center.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyou.hithere.center.config.ConfigCenter;
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
	@Autowired
	private ConfigCenter configCenter;

	@RequestMapping(value = "query")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {
		BaseRet br = new BaseRet();
		Map<String, Object> map = configCenter.getPublicConfig();
		br.setData(map);
		return br.toString();
	}
}

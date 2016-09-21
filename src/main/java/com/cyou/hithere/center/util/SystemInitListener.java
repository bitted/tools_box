/**
 *  Copyright (c)  2011-2020 Changyou, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Changyou, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Changyou.
 */
package com.cyou.hithere.center.util;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;



/**
 * 系统启动加载类
 * 
 * @author zhangxinglong
 * @date 2014-4-19
 */
public class SystemInitListener extends ContextLoaderListener {

	private static Logger logger = LoggerFactory.getLogger(SystemInitListener.class);
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		super.contextInitialized(sce);
		
		logger.info("hithere.center Server SUCCESS......................");

	}

	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("hithere.center DESTROY......................");
	}

}

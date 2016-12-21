/**
 * 项目名称：tools_box
 * 包名：com.cyou.hithere.center.util.concurrent
 * 文件名：Producer.java
 * 版本信息：V1.0
 * 日期：2016年12月20日-下午4:42:26
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.concurrent.abq;

import java.util.concurrent.BlockingQueue;

/**
 * 类名称：Producer
 * 类描述：(生产者)
 *
 * 创建人：litaijun
 * 创建时间：2016年12月20日 下午4:42:26
 * 修改人：
 * 修改时间：2016年12月20日 下午4:42:26
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class Producer implements Runnable {

	private BlockingQueue<String> queue = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Producer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			queue.put("1");
			Thread.sleep(1000);
			queue.put("2");
			Thread.sleep(1000);
			queue.put("3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

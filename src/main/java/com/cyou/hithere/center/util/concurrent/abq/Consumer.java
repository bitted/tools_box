/**
 * 项目名称：tools_box
 * 包名：com.cyou.hithere.center.util.concurrent
 * 文件名：Consumer.java
 * 版本信息：V1.0
 * 日期：2016年12月20日-下午4:46:14
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.concurrent.abq;

import java.util.concurrent.BlockingQueue;

/**
 * 类名称：Consumer
 * 类描述：(消费者)
 *
 * 创建人：litaijun
 * 创建时间：2016年12月20日 下午4:46:14
 * 修改人：
 * 修改时间：2016年12月20日 下午4:46:14
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class Consumer implements Runnable {
	@SuppressWarnings("rawtypes")
	private BlockingQueue queue = null;

	@SuppressWarnings("rawtypes")
	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

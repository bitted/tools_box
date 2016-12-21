/**
 * 项目名称：tools_box
 * 包名：com.cyou.hithere.center.util.concurrent.dq
 * 文件名：DelayQueueTest.java
 * 版本信息：V1.0
 * 日期：2016年12月20日-下午4:55:51
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.concurrent.dq;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * 类名称：DelayQueueTest
 * 类描述：(延迟队列)
 *
 * 创建人：litaijun
 * 创建时间：2016年12月20日 下午4:55:51
 * 修改人：
 * 修改时间：2016年12月20日 下午4:55:51
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class DelayQueueTest {

	/**
	 * main(这里用一句话描述这个方法的作用)
	 * 
	 * @param args
	 *            返回类型：void
	 * @throws InterruptedException 
	 * @exception
	 * @since 1.0.0
	 */

	public static void main(String[] args) throws InterruptedException {
		DelayQueue queue = new DelayQueue();
		Delayed element = new DelayedElement();
		queue.put(element);
		Delayed element2 = queue.take();

	}

}

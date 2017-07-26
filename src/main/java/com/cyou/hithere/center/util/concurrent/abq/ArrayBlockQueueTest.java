/**
 * 项目名称：tools_box
 * 包名：com.cyou.hithere.center.util.concurrent
 * 文件名：BlockQueueTest.java
 * 版本信息：V1.0
 * 日期：2016年12月20日-下午4:37:15
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.concurrent.abq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 类名称：BlockQueueTest
 * 类描述：(ArrayBlockQueue阻塞队列处理)
 *
 * 创建人：litaijun
 * 创建时间：2016年12月20日 下午4:37:15
 * 修改人：
 * 修改时间：2016年12月20日 下午4:37:15
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class ArrayBlockQueueTest {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws InterruptedException {
		// ArrayBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
		BlockingQueue queue = new ArrayBlockingQueue(1024);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(5000);
	}
}

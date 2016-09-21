/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.test
 * 文件名：ThreadTest.java
 * 版本信息：V1.0
 * 日期：2015-3-30-下午04:45:54
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.cyou.hithere.center.util.ThreadPoolExecutorTaskUtils;

/**
 * 类名称：ThreadTest
 * 类描述：(测试多线程)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-30 下午04:45:54
 * 修改人：
 * 修改时间：2015-3-30 下午04:45:54
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class ThreadTest extends TestBase {

	final ExecutorService pool = Executors.newFixedThreadPool(10);

	@Test
	public void testSingleThreadExecutor() {
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newSingleThreadExecutor();
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}

	@Test
	public void testFixedThreadPool() {
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(6);
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}

	@Test
	public void testCachedThreadPool() {
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newCachedThreadPool();
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}

	@Test
	public void testScheduledThreadPoolExecutor() {
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {// 每隔一段时间就触发异常
					@Override
					public void run() {
						// throw new RuntimeException();
						System.out.println("================");
					}
				}, 1000, 5000, TimeUnit.MILLISECONDS);
		exec.scheduleAtFixedRate(new Runnable() {// 每隔一段时间打印系统时间，证明两者是互不影响的
					@Override
					public void run() {
						System.out.println(System.nanoTime());
					}
				}, 1000, 2000, TimeUnit.MILLISECONDS);
	}

	@Test
	public void testprint() {
		ThreadPoolExecutorTaskUtils.getThreadPoolExecutor().execute(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					System.out.println(i);
				}
			}
		});
	}

	@Entity(value = "books1", noClassnameStored = true)
	private static final class Book {
		@Id
		private ObjectId id;
		private String title;
		private String author;
		private Integer copies;
		private List<String> tags;

		private Book() {
		}

		private Book(final String title, final String author, final Integer copies, final String... tags) {
			this.title = title;
			this.author = author;
			this.copies = copies;
			this.tags = Arrays.asList(tags);
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}
}

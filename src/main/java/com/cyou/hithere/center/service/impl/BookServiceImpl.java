/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.hithere.center.service.impl
 * 文件名：BookServiceImpl.java
 * 版本信息：V1.0
 * 日期：2015-3-30-下午05:18:13
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.service.impl;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyou.hithere.center.dao.BookDao;
import com.cyou.hithere.center.domain.Book;
import com.cyou.hithere.center.service.BookService;
import com.cyou.hithere.center.util.ThreadPoolExecutorTaskUtils;
import com.google.common.collect.Lists;

/**
 * 类名称：BookServiceImpl
 * 类描述：(描述此类所在架构中层次，如：某某服务接口实现、某某实体模型、某某工具类等----必填)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-30 下午05:18:13
 * 修改人：
 * 修改时间：2015-3-30 下午05:18:13
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Component
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	ExecutorService pool = Executors.newFixedThreadPool(50);

	@Override
	public void saveBook() {
		final Long start = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			ThreadPoolExecutorTaskUtils.getInstance().executeTask(new Runnable() {
				@Override
				public void run() {
					Random random = new Random();
					List<Book> list = Lists.newArrayList();
					for (int i = 0; i < 10000000; i++) {
						// list.add(new Book("mongodb title" + i, "username" + random.nextInt(20), random.nextInt(10)));
						bookDao.getDS().save(new Book("mongodb title", "username" + random.nextInt(20), random.nextInt(10)));
					}
					System.out.println("end task");
					Long end = System.currentTimeMillis();
					System.out.println("task time is =" + (end - start));
				}
			});
		}
	}
	// public void saveBook() {
	// for (int i = 0; i < 1000; i++) {
	// pool.execute(new Runnable() {
	// @Override
	// public void run() {
	// Random random = new Random();
	// for (int i = 0; i < 100000; i++) {
	// bookDao.getDS().save(new Book("mongodb title" + i, "username" + random.nextInt(20), random.nextInt(10)));
	// System.out.println(i);
	// }
	// System.out.println("end task");
	// }
	// });
	// }
	// }
	// public void saveBook() {
	// ThreadPoolExecutorTaskUtils.getThreadPoolExecutor().execute(new Runnable() {
	// @Override
	// public void run() {
	// Random random = new Random();
	// for (int i = 0; i < 100000; i++) {
	// bookDao.getDS().save(new Book("mongodb title" + i, "username" + random.nextInt(20), random.nextInt(10)));
	// System.out.println(i);
	// }
	// System.out.println("end task");
	// }
	// });
	// }

}

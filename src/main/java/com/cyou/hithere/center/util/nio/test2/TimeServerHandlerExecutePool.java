/**
 * 
 */
package com.cyou.hithere.center.util.nio.test2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * 
 */
public class TimeServerHandlerExecutePool {

	private ExecutorService executor;

	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime()
				.availableProcessors(), maxPoolSize, 1200L, TimeUnit.SECONDS,
				new ArrayBlockingQueue<java.lang.Runnable>(queueSize));
	}

	public void execute(java.lang.Runnable task) {
		executor.execute(task);
	}
}

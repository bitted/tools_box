package com.cyou.hithere.center.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类名称：ThreadPoolExecutorTaskUtils
 * 类描述：(线程池任务工具类)
 * (可处理多线程任务）
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-30 下午04:06:40
 * 修改人：
 * 修改时间：2015-3-30 下午04:06:40
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class ThreadPoolExecutorTaskUtils {
	/**
	 * corePoolSize： 池中所保存的线程数，包括空闲线程。
	 * 
	 * maximumPoolSize：池中允许的最大线程数。
	 * 
	 * keepAliveTime： 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
	 * 
	 * unit： keepAliveTime 参数的时间单位。
	 * 
	 * workQueue： 执行前用于保持任务的队列。此队列仅保持由 execute方法提交的 Runnable任务。
	 * 
	 * handler：由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。
	 **/

	private static ThreadPoolExecutor poolExecutor;

	private static ThreadPoolExecutorTaskUtils instance;

	private static int corePoolSize = 20;

	private static int maximumPoolSize = 50;

	private static long keepAliveTime = 60;

	private static int queueSize = 1000;

	public ThreadPoolExecutorTaskUtils() {
		if (poolExecutor == null) {
			poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(queueSize), new MyHandler());
		}
	}

	/**
	 * 
	 * getInstance(获取线程池单例)
	 * 
	 * @return
	 *         返回类型：ThreadPoolExecutorTaskUtils
	 * @exception
	 * @since 1.0.0
	 */
	public static synchronized ThreadPoolExecutorTaskUtils getInstance() {
		if (instance == null) {
			instance = new ThreadPoolExecutorTaskUtils();
		}
		return instance;
	}

	/**
	 * 
	 * getThreadPoolExecutor(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * 
	 * @return
	 *         返回类型：ThreadPoolExecutor
	 * @exception
	 * @since 1.0.0
	 */
	public static synchronized ThreadPoolExecutor getThreadPoolExecutor() {
		if (poolExecutor == null) {
			poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(queueSize), new MyHandler());
			// new ArrayBlockingQueue<Runnable>(queueSize), new ThreadPoolExecutor.CallerRunsPolicy());
		}
		return poolExecutor;

	}

	public void executeTask(Runnable command) {
		poolExecutor.execute(command);
	}
}

class MyHandler implements RejectedExecutionHandler {

	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang.Runnable,
	 * java.util.concurrent.ThreadPoolExecutor)
	 */
	private static int rejectedCount = 1;

	@Override
	public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {
		System.out.println("拒绝的任务" + rejectedCount++);
		arg0.run();// 运行完被抛弃的任务
		System.out.println("完成任务数：" + arg1.getCompletedTaskCount());
		System.out.println("总任务数：" + arg1.getTaskCount());

	}

}

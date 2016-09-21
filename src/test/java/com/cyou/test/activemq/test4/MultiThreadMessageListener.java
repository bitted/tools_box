package com.cyou.test.activemq.test4;

import java.util.concurrent.ExecutorService;

import javax.jms.Message;
import javax.jms.MessageListener;


/**
 * 消息消费者中使用的多线程消息监听服务
 * @author linwei
 *
 */
public class MultiThreadMessageListener implements MessageListener {

	//默认线程池数量
	public final static int DEFAULT_HANDLE_THREAD_POOL=10;
	//最大的处理线程数.
	private int maxHandleThreads;
	//提供消息回调调用接口
	private MessageHandler messageHandler;

	private ExecutorService handleThreadPool;
	
	
	public MultiThreadMessageListener(MessageHandler messageHandler){
		this(DEFAULT_HANDLE_THREAD_POOL, messageHandler);
	}
	
	public MultiThreadMessageListener(int maxHandleThreads,MessageHandler messageHandler){
		this.maxHandleThreads=maxHandleThreads;
		this.messageHandler=messageHandler;
		//支持阻塞的固定大小的线程池(自行手动创建的)
		this.handleThreadPool = new FixedAndBlockedThreadPoolExecutor(this.maxHandleThreads);
	}
	
	
	/**
	 * 监听程序中自动调用的方法
	 */
	@Override
	public void onMessage(final Message message) {
		//使用支持阻塞的固定大小的线程池来执行操作
		this.handleThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					MultiThreadMessageListener.this.messageHandler.handle(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

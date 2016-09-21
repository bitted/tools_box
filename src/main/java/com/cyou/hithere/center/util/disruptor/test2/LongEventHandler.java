package com.cyou.hithere.center.util.disruptor.test2;

import com.lmax.disruptor.EventHandler;
/**
 * 3.定义事件处理的具体实现
 * 通过实现接口 com.lmax.disruptor.EventHandler<T> 定义事件处理的具体实现。
 * @author totyuZWL
 *
 */
public class LongEventHandler implements EventHandler<LongEvent> {
	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
			throws Exception{
//			Thread.sleep(10);
			System.out.println("消费完毕: " + event.getMessage());
	}
}

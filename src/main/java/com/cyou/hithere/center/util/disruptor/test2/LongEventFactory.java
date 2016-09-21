package com.cyou.hithere.center.util.disruptor.test2;

import com.lmax.disruptor.EventFactory;

/**
 * 2.定义事件工厂
 * 事件工厂(Event Factory)定义了如何实例化前面第1步中定义的事件(Event)，需要实现接口 com.lmax.disruptor.EventFactory<T>。
 * Disruptor 通过 EventFactory 在 RingBuffer 中预创建 Event 的实例。
 * @author totyuZWL
 *
 */
public class LongEventFactory implements EventFactory<LongEvent>{

	@Override
	public LongEvent newInstance() {
		// TODO Auto-generated method stub
		return new LongEvent();
	}

}

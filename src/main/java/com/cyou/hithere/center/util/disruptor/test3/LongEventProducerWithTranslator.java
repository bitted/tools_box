package com.cyou.hithere.center.util.disruptor.test3;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class LongEventProducerWithTranslator {
	// 一个translator可以看做一个事件初始化器，publicEvent方法会调用它
	// 填充Event
	private static final EventTranslatorOneArg<LongEvent, String> TRANSLATOR = new EventTranslatorOneArg<LongEvent, String>() {
		@Override
		public void translateTo(LongEvent event, long sequence,String message) {
			event.setMessage(message);
		}
	};
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(String message) {
		ringBuffer.publishEvent(TRANSLATOR, message);
	}
}

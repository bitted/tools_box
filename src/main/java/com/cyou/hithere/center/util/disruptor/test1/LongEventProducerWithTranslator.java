package com.cyou.hithere.center.util.disruptor.test1;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class LongEventProducerWithTranslator {
	private final RingBuffer<LongEvent> ringBuffer;
	// 一个translator可以看做一个事件初始化器，publicEvent方法会调用它
	// 填充Event
	private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
		@Override
		public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
			event.setValue(bb.getLong(0));
		}
	};

	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer bb) {
		ringBuffer.publishEvent(TRANSLATOR, bb);
	}
}
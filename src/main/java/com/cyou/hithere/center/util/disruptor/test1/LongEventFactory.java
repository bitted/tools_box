package com.cyou.hithere.center.util.disruptor.test1;

import com.lmax.disruptor.EventFactory;

@SuppressWarnings("rawtypes")
public class LongEventFactory implements EventFactory {
	@Override
	public Object newInstance() {
		return new LongEvent();
	}
}
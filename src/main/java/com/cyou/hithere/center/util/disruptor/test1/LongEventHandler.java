package com.cyou.hithere.center.util.disruptor.test1;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
	@Override
	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
		System.out.println("value="+longEvent.getValue());
	}
}
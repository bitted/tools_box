package com.cyou.hithere.center.util.disruptor.test3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class DisruptorUtils {

	private static Disruptor<LongEvent> disruptor = null;
	
	private static ExecutorService executor = null;
		
	@SuppressWarnings("unchecked")
	public static LongEventProducer start(){
		int bufferSize = 1024; //队列大小
		//多个线程
		executor = Executors.newCachedThreadPool();
		disruptor = new Disruptor<LongEvent>(new LongEventFactory(),bufferSize, executor);
		disruptor.handleEventsWith(new LongEventHandler());
		disruptor.start();
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		LongEventProducer producer = new LongEventProducer(ringBuffer);
		return producer;
	}
	
	
	@SuppressWarnings("unchecked")
	public static LongEventProducerWithTranslator start1(){
		int bufferSize = 8;//队列大小
		//多个线程
		executor = Executors.newCachedThreadPool();
		disruptor = new Disruptor<LongEvent>(new LongEventFactory(),bufferSize, executor);
		disruptor.handleEventsWith(new LongEventHandler());
		disruptor.start();
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		LongEventProducerWithTranslator producerw = new LongEventProducerWithTranslator(ringBuffer);
		return producerw;
	}
	
	public static void shutdown(){
		disruptor.shutdown();
		executor.shutdown();
	}
}

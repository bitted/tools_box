package com.cyou.hithere.center.util.disruptor.test3;

public class InitTest {
	
	public static void main(String[] args) throws InterruptedException {
		//LongEventProducer producer = DisruptorUtils.start();
		LongEventProducerWithTranslator producer = DisruptorUtils.start1();
		for (long l = 0; l < 100; l++) {
//			Thread.sleep(100);
			String message = "你好：" + l;
			producer.onData(message);
			System.out.println("生产完毕：" + message);
		}
		DisruptorUtils.shutdown();
	}
}

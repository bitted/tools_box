package com.cyou.hithere.center.util.disruptor.test2;

public class InitTest {
	
	public static void main(String[] args) throws Exception {
		DisruptorUtils.start();
		for(int i= 0;i<100;i++){
			DisruptorUtils.produce("你好!"+i);
			System.out.println("生产完毕:你好!"+i);
		}
		DisruptorUtils.shutdown();
	}
}

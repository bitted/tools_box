package com.cyou.hithere.center.util;

public class TreadTest {

	public static void main(String[] args) {
		for (int i = 0; i < 1000000; i++) {
			System.out.println("i=" + i);
		}
		Long start = System.currentTimeMillis();
		System.out.println("start=" + start);
		ThreadPoolExecutorTaskUtils.getInstance().executeTask(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 1000000; i++) {
					System.out.println("i=" + i);
				}
			}
		});
		Long end = System.currentTimeMillis();
		System.out.println("end=" + (end - start) / 1000);
	}
}

package com.cyou.test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行。。。");
	}

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {// 每隔一段时间就触发异常
					@Override
					public void run() {
						// throw new RuntimeException();
						System.out.println("================");
					}
				}, 1, 5, TimeUnit.SECONDS);//每5秒执行一次
		exec.scheduleAtFixedRate(new Runnable() {// 每隔一段时间打印系统时间，证明两者是互不影响的
					@Override
					public void run() {
						System.out.println(System.nanoTime());
					}
				}, 1, 10, TimeUnit.SECONDS);//每10秒执行一次
	}
}

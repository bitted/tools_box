package com.cyou.test;

import java.util.Date;

class TimePrinter /*extends Thread*/ implements Runnable {
	int pauseTime;
	String name;

	public TimePrinter(int x, String n) {
		pauseTime = x;
		name = n;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println(name + ":" + new Date(System.currentTimeMillis()));
				Thread.sleep(pauseTime);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	static public void main(String args[]) {
//		TimePrinter tp1 = new TimePrinter(1000, "Fast Guy");
		Thread tp1 = new Thread(new TimePrinter(1000, "Fast Guy"));
		tp1.start();
//		TimePrinter tp2 = new TimePrinter(3000, "Slow Guy");
		Thread tp2 = new Thread(new TimePrinter(3000, "Fast Guy"));
		tp2.start();

	}
}
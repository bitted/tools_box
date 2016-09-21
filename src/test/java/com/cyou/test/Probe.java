package com.cyou.test;

public class Probe extends Thread {
	public Probe() {
	}

	@Override
	public void run() {
		while (true) {
			Thread[] x = new Thread[100];
			Thread.enumerate(x);
			for (int i = 0; i < 100; i++) {
				Thread t = x[i];
				if (t == null)
					break;
				else
					System.out.println(t.getName() + "\t" + t.getPriority() + "\t" + t.isAlive() + "\t" + t.isDaemon());
			}
		}
	}
	
	public static void main(String[] args) {
		new Probe().start();
	}
}
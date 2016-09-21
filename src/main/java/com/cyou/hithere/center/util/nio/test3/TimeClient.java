package com.cyou.hithere.center.util.nio.test3;

import java.io.IOException;

public class TimeClient {
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10000; i++) {
			run(args, i);
			System.out.println(" =======i= " + i);
		}
	}

	private static void run(String[] args, int count) throws IOException {

		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}

		new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001")
				.start();
	}
}

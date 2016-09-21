package com.cyou.hithere.center.util.nio.test1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	public static void main(String[] args) {
		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}
		ServerSocket server = null;

		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port:" + port);
			Socket socket = null;
			while (true) {
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} catch (Exception e) {
		} finally {
			if (server != null) {
				System.out.println("The time server close");
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				server = null;
			}
		}
	}
}

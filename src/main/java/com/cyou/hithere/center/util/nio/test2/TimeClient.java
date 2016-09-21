package com.cyou.hithere.center.util.nio.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10000; i++) {
			run(args,i);
			System.out.println(" =======i= " + i);
		}
	}

	private static void run(String[] args,int count) throws IOException {

		int port = 8080;
		if (args != null && args.length > 0) {
			port = Integer.valueOf(args[0]);
		}

		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("QUERY TIME ORDER "+ count);
			System.out.println("Send order 2 server succeed.");
			String resp = in.readLine();
			System.out.println("Now is :" + resp);
		} catch (Exception e) {
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
			if (in != null) {
				in.close();
				in = null;
			}
			if (socket != null) {
				socket.close();
				socket = null;
			}
		}
	}
}

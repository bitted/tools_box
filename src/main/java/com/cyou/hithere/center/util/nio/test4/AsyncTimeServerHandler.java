///**
// * 
// */
//package com.cyou.hithere.center.util.nio.test4;
//
//import java.net.InetSocketAddress;
//import java.nio.channels.AsynchronousServerSocketChannel;
//import java.util.concurrent.CountDownLatch;
//
///**
// * @author Administrator
// * 
// */
//public class AsyncTimeServerHandler implements Runnable {
//
//	private int port;
//
//	CountDownLatch latch;
//	AsynchronousServerSocketChannel asynchronousServerSocketChannel;
//
//	public AsyncTimeServerHandler(int port) {
//		this.port = port;
//		try {
//			asynchronousServerSocketChannel = AsynchronousServerSocketChannel
//					.open();
//			asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
//			System.out.println("The time server is start in port : " + port);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void run() {
//		// 在完成一组正在执行的操作之前，允许当前的线程一直阻塞。在本例中，我们让线程再次阻塞，防止服务端执行完成退出。
//		latch = new CountDownLatch(1);
//		doAccept();
//		try {
//			latch.await();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void doAccept() {
//		asynchronousServerSocketChannel.accept(this,
//				new AcceptCompletionHandler());
//	}
//
//}

///**
// * 
// */
//package com.cyou.hithere.center.util.nio.test4;
//
//import java.io.UnsupportedEncodingException;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//import java.util.concurrent.CountDownLatch;
//
///**
// * @author Administrator
// * 
// */
//public class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler>, Runnable {
//
//	private String host;
//	private int port;
//	private AsynchronousSocketChannel client;
//	private CountDownLatch latch;
//
//	public AsyncTimeClientHandler(String host, int port) {
//		this.host = host;
//		this.port = port;
//		try {
//			client = AsynchronousSocketChannel.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void run() {
//		latch = new CountDownLatch(1);
//		client.connect(new InetSocketAddress(host, port), this, this);
//		try {
//			latch.await();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			client.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * @see java.nio.channels.CompletionHandler#completed(java.lang.Object,
//	 * java.lang.Object)
//	 */
//	public void completed(Void result, AsyncTimeClientHandler attachment) {
//		byte[] req = "TIME".getBytes();
//		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
//		writeBuffer.put(req);
//		writeBuffer.flip();
//		client.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
//
//			public void completed(Integer result, ByteBuffer buffer) {
//				if (buffer.hasRemaining()) {
//					client.write(buffer, buffer, this);
//				} else {
//					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
//					client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
//
//						public void completed(Integer result, ByteBuffer buffer) {
//							buffer.flip();
//							byte[] bytes = new byte[buffer.remaining()];
//							buffer.get(bytes);
//							String body;
//							try {
//								body = new String(bytes, "UTF-8");
//								System.out.println("Now is : " + body);
//
//							} catch (UnsupportedEncodingException e) {
//								e.printStackTrace();
//							}
//						}
//
//						public void failed(Throwable exc, ByteBuffer attachment) {
//							try {
//								client.close();
//								latch.countDown();
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//
//						}
//
//					});
//				}
//
//			}
//
//			public void failed(Throwable exc, ByteBuffer attachment) {
//				try {
//					client.close();
//					latch.countDown();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//		});
//
//	}
//
//	public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
//		try {
//			client.close();
//			latch.countDown();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}

//package com.cyou.hithere.center.util.nio.test4;
//
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//
//public class ReadCompletionHandler implements
//		CompletionHandler<Integer, ByteBuffer> {
//	private AsynchronousSocketChannel channel;
//
//	public ReadCompletionHandler(AsynchronousSocketChannel channel) {
//		if (this.channel == null) {
//			this.channel = channel;
//		}
//	}
//
//	public void completed(Integer result, ByteBuffer attachment) {
//		attachment.flip();
//		byte[] body = new byte[attachment.remaining()];
//		attachment.get(body);
//		try {
//			String req = new String(body, "UTF-8");
//			System.out.println("The time server recerve order : " + req);
//			String currentTime = "TIME".equalsIgnoreCase(req) ? new java.util.Date(
//					System.currentTimeMillis()).toString() : "BAD ORDER";
//			doWrite(currentTime);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 
//	 */
//	private void doWrite(String time) {
//		if (time != null && time.trim().length() > 0) {
//			byte[] bytes = time.getBytes();
//			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
//			writeBuffer.put(bytes);
//			writeBuffer.flip();
//			channel.write(writeBuffer, writeBuffer,
//					new CompletionHandler<Integer, ByteBuffer>() {
//						public void completed(Integer result, ByteBuffer buffer) {
//							// 如果没有发送完成，继续发送
//							if (buffer.hasRemaining()) {
//								channel.write(buffer, buffer, this);
//							}
//						}
//
//						public void failed(Throwable exc, ByteBuffer attachment) {
//							try {
//								channel.close();
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//					});
//		}
//	}
//
//	public void failed(Throwable exc, ByteBuffer attachment) {
//		try {
//			this.channel.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}

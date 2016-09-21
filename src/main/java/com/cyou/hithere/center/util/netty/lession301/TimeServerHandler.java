/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession301
 * 文件名：TimeServerHandler.java
 * 版本信息：V1.0
 * 日期：2015-9-14-下午03:18:31
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession301;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	/**
	 * 接收消息，处理业务逻辑，返回结果。
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);

		String body = new String(req, "UTF-8");
		System.out.println("The time server receive order :" + body);
		String currentTime = "TIME".equalsIgnoreCase(body) ? new Date(
				System.currentTimeMillis()).toString() : "BAD ORDER";
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		// netty的write方法并不直接将消息写入socketchannel中，调用write方法只是把待发送的消息放到发送缓存数组中，再通过调用flush方法，将发送缓冲区中的消息全部写到socketchannel中。
		ctx.write(resp);
	}

	/**
	 * 将消息发送队列中的消息写入到SocketChannel中发送给对方。
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	/**
	 * 异常捕获
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}

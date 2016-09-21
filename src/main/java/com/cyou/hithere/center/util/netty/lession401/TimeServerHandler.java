/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession301
 * 文件名：TimeServerHandler.java
 * 版本信息：V1.0
 * 日期：2015-9-14-下午03:18:31
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession401;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHandler extends ChannelHandlerAdapter {

	private int counter;

	/**
	 * 接收消息，处理业务逻辑，返回结果。
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println("The time server receive order :" + body + " ; the counter is :" + ++counter);
		String currentTime = "TIME".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
		currentTime = currentTime + System.getProperty("line.separator");
		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
		// netty的writeAndFlush方法直接把待发送的消息放到发送socketchannel中。
		ctx.writeAndFlush(resp);
	}

	/**
	 * 异常捕获
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

}

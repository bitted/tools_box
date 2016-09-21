/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession301
 * 文件名：TimeClientHandler.java
 * 版本信息：V1.0
 * 日期：2015-9-14-下午03:49:40
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession301;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.apache.log4j.Logger;

/**
 * 类名称：TimeClientHandler
 * 类描述：(Page74)
 * 
 * 创建人：Administrator
 * 创建时间：2015-9-14 下午03:52:06
 * 修改人：
 * 修改时间：2015-9-14 下午03:52:06
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
	private final ByteBuf firstMessage;

	public TimeClientHandler() {
		byte[] req = "TIME".getBytes();
		firstMessage = Unpooled.buffer(req.length);
		firstMessage.writeBytes(req);
	}

	/**
	 * 当客户端和服务端tcp链路建立成功后，netty的NIO线程会调用，发送查询时间的指令给服务端，调用ChannelHandlerContext的writeAndFlush方法将请求消息发送给服务端。
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		ctx.writeAndFlush(firstMessage);
	}

	/**
	 * 当服务端返回应答消息时，该方法被调用。
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println("Now is : " + body);
	}

	/**
	 * 异常处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// 释放资源
		logger.warn("Unexcepted exception from downstram : " + cause.getMessage());
		ctx.close();
	}
}

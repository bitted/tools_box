/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession501
 * 文件名：EchoClientHandler.java
 * 版本信息：V1.0
 * 日期：2015-9-15-下午04:38:23
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession501;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 类名称：EchoClientHandler
 * 类描述：(P98)
 * 
 * 创建人：Administrator
 * 创建时间：2015-9-15 下午04:38:23
 * 修改人：
 * 修改时间：2015-9-15 下午04:38:23
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class EchoClientHandler extends ChannelHandlerAdapter {
	private int counter;
	static final String ECHO_REQ = "Hi LiJian.Welcome to Netty.$_";

	public EchoClientHandler() {
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		for (int i = 0; i < 100; i++) {
			ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("This is " + ++counter + " times receive server : [" + msg + "]");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}

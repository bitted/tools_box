/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession501
 * 文件名：EchoServerHandler.java
 * 版本信息：V1.0
 * 日期：2015-9-15-下午04:19:03
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession501;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 类名称：EchoServerHandler
 * 类描述：(P96)
 * 
 * 创建人：Administrator
 * 创建时间：2015-9-15 下午04:19:03
 * 修改人：
 * 修改时间：2015-9-15 下午04:19:03
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class EchoServerHandler extends ChannelHandlerAdapter {
	int counter = 0;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		System.out.println("This is " + ++counter + " times receive client :[" + body + "]");
		body += "$_";
		ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
		ctx.writeAndFlush(echo);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();// 发生异常，关闭链路
	}
}

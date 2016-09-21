/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession502
 * 文件名：EchoServerHandler.java
 * 版本信息：V1.0
 * 日期：2015-10-27-上午11:11:04
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession502;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 类名称：EchoServerHandler
 * 类描述：(描述此类所在架构中层次，如：某某服务接口实现、某某实体模型、某某工具类等----必填)
 * 
 * 创建人：Administrator
 * 创建时间：2015-10-27 上午11:11:04
 * 修改人：
 * 修改时间：2015-10-27 上午11:11:04
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Receive client : [" + msg + "]");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();// 发生异常，关闭链路
	}
}

///**
// * 项目名称：hithere-center
// * 包名：com.cyou.hithere.center.util.netty.lession501
// * 文件名：EchoClient.java
// * 版本信息：V1.0
// * 日期：2015-9-15-下午04:25:58
// * 作者：Administrator
// * Copyright (c) 2015畅游天下-版权所有
// */
//
//package com.cyou.hithere.center.util.netty.lession501;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.codec.string.StringDecoder;
//
///**
// * 类名称：EchoClient
// * 类描述：(P97)
// * 
// * 创建人：Administrator
// * 创建时间：2015-9-15 下午04:25:58
// * 修改人：
// * 修改时间：2015-9-15 下午04:25:58
// * 修改备注：
// * 
// * @version 1.0.0
// */
//
//public class EchoClient {
//	public void connect(int port, String host) throws Exception {
//		// 配置客户端NIO线程组
//		EventLoopGroup group = new NioEventLoopGroup();
//		try {
//			Bootstrap b = new Bootstrap();
//			b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
//
//				@Override
//				protected void initChannel(SocketChannel ch) throws Exception {
//					ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//					ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
//					ch.pipeline().addLast(new StringDecoder());
//					ch.pipeline().addLast(new EchoClientHandler());
//				}
//			});
//			// 发起异步连接操作
//			ChannelFuture f = b.connect(host, port).sync();
//			// 等待客户端链路关闭
//			f.channel().closeFuture().sync();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 优雅退出，释放NIO线程组
//			group.shutdownGracefully();
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//		int port = 8080;
//		if (args != null && args.length > 0) {
//			try {
//				port = Integer.valueOf(port);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		new EchoClient().connect(port, "127.0.0.1");
//	}
//}

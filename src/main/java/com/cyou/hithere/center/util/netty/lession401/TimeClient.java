///**
// * 项目名称：hithere-center
// * 包名：com.cyou.hithere.center.util.netty.lession301
// * 文件名：TimeClient.java
// * 版本信息：V1.0
// * 日期：2015-9-14-下午03:36:05
// * 作者：Administrator
// * Copyright (c) 2015畅游天下-版权所有
// */
//
//package com.cyou.hithere.center.util.netty.lession401;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.LineBasedFrameDecoder;
//import io.netty.handler.codec.string.StringDecoder;
//
///**
// * 类名称：TimeClient
// * 类描述：(Page73)
// * 
// * 创建人：Administrator
// * 创建时间：2015-9-14 下午03:45:16
// * 修改人：
// * 修改时间：2015-9-14 下午03:45:16
// * 修改备注：
// * 
// * @version 1.0.0
// */
//public class TimeClient {
//
//	public void connect(int port, String host) throws Exception {
//		EventLoopGroup group = new NioEventLoopGroup();
//
//		try {
//			Bootstrap b = new Bootstrap();
//			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
//					.handler(new ChannelInitializer<SocketChannel>() {
//						public void initChannel(SocketChannel ch) throws Exception {
//							//使用和服务端相同的解码器
//							ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//							ch.pipeline().addLast(new StringDecoder());
//							ch.pipeline().addLast(new TimeClientHandler());
//						}
//					});
//			// 发起异步连接操作
//			ChannelFuture f = b.connect(host, port).sync();
//			System.out.println("启动客户端成功！");
//			// 等待客户端链路关闭
//			f.channel().closeFuture().sync();
//
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
//				port = Integer.valueOf(args[0]);
//			} catch (Exception e) {
//			}
//		}
//		new TimeClient().connect(port, "127.0.0.1");
//	}
//
//}

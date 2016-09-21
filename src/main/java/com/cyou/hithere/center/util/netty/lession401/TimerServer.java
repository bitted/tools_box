///**
// * 项目名称：hithere-center
// * 包名：com.cyou.hithere.center.util.netty.lession301
// * 文件名：TimeServer.java
// * 版本信息：V1.0
// * 日期：2015-9-14-下午02:49:18
// * 作者：Administrator
// * Copyright (c) 2015畅游天下-版权所有
// */
//
//package com.cyou.hithere.center.util.netty.lession401;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.LineBasedFrameDecoder;
//import io.netty.handler.codec.string.StringDecoder;
//
//public class TimerServer {
//	public void bind(int port) throws Exception {
//		// 配置服务端的NIO线程组
//		EventLoopGroup bossGroup = new NioEventLoopGroup();
//		EventLoopGroup workerGroup = new NioEventLoopGroup();
//		try {
//			ServerBootstrap b = new ServerBootstrap();
//			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
//					.childHandler(new ChildChannelHandler());
//			// 绑定端口，同步等待成功
//			ChannelFuture f = b.bind(port).sync();
//			System.out.println("启动服务端成功！");
//			// 等待服务端监听端口关闭
//			f.channel().closeFuture().sync();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			bossGroup.shutdownGracefully();
//			workerGroup.shutdownGracefully();
//		}
//	}
//
//	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
//
//		@Override
//		protected void initChannel(SocketChannel ch) throws Exception {
//			//新增两个解码器，解决tcp的粘包问题
//			ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//			ch.pipeline().addLast(new StringDecoder());
//			
//			ch.pipeline().addLast(new TimeServerHandler());
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//		int port = 8080;
//		if (args != null && args.length > 0) {
//			try {
//				port = Integer.valueOf(args[0]);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		new TimerServer().bind(port);
//	}
//}

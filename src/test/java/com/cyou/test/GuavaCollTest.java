/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.test
 * 文件名：GuavaCollTest.java
 * 版本信息：V1.0
 * 日期：2015-4-9-下午06:06:38
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import junit.framework.TestCase;

/**
 * 类名称：GuavaCollTest
 * 类描述：(演示guava中集合包的使用)
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-9 下午06:06:38
 * 修改人：
 * 修改时间：2015-4-9 下午06:06:38
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class GuavaCollTest extends TestCase {

	public static void fileChannelCopy(File s, File t) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void copy(File s, File t) throws IOException {
		InputStream fis = null;
		OutputStream fos = null;

		try {
			fis = new BufferedInputStream(new FileInputStream(s));
			fos = new BufferedOutputStream(new FileOutputStream(t));
			byte[] buf = new byte[12048];
			int i;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fis.close();
			fos.close();
		}
	}

	/**
	 * main(7MB两种数据copy耗时比较)
	 * FileChannel复制，用时：15ms
	 * Buffered复制，用时：23ms
	 * 
	 * @param args
	 * @throws IOException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	public static void main(String[] args) throws IOException {
		File s = new File("D:\\test.zip");
		File t = new File("D:\\to.zip");
		long start, end;
		start = System.currentTimeMillis();

		fileChannelCopy(s, t);
		end = System.currentTimeMillis();
		System.out.println("FileChannel复制，用时：" + (end - start) + "ms");

		start = System.currentTimeMillis();
		copy(s, t);// 缓冲输入输出流方式复制
		end = System.currentTimeMillis();
		System.out.println("Buffered复制，用时：" + (end - start) + "ms");
	}
}

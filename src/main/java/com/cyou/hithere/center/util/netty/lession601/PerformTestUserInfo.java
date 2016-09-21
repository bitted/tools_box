/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession601
 * 文件名：PerformTestUserInfo.java
 * 版本信息：V1.0
 * 日期：2015-11-3-下午03:31:45
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession601;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * 类名称：PerformTestUserInfo
 * 类描述：(描述此类所在架构中层次，如：某某服务接口实现、某某实体模型、某某工具类等----必填)
 * 
 * 创建人：Administrator
 * 创建时间：2015-11-3 下午03:31:45
 * 修改人：
 * 修改时间：2015-11-3 下午03:31:45
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class PerformTestUserInfo {
	public static void main(String[] args) throws IOException {
		UserInfo info = new UserInfo();
		info.buildUserID(100).buildUserName("Welcome to Netty");
		int loop = 1000000;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(info);
			os.flush();
			os.close();
			byte[] b = bos.toByteArray();
			bos.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("The jdk serializable cost time is : " + (endTime - startTime) + "ms");
		System.out.println("===================================================");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			byte[] b = info.codeC(buffer);
		}
		endTime = System.currentTimeMillis();
		System.out.println("The byte array serializable cost time is : " + (endTime - startTime) + "ms");

	}
}

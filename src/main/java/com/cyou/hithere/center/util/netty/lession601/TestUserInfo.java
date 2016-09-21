/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession601
 * 文件名：TestUserInfo.java
 * 版本信息：V1.0
 * 日期：2015-11-3-下午03:23:00
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession601;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 类名称：TestUserInfo
 * 类描述：(测试性能)
 * 
 * 创建人：Administrator
 * 创建时间：2015-11-3 下午03:23:00
 * 修改人：
 * 修改时间：2015-11-3 下午03:23:00
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class TestUserInfo {

	public static void main(String[] args) throws IOException {
		UserInfo info = new UserInfo();
		info.buildUserID(100).buildUserName("Welcome to Netty");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		os.writeObject(info);
		os.flush();
		os.close();
		byte[] b = bos.toByteArray();
		System.out.println("The jdk serializable length is : " + b.length);
		bos.close();
		System.out.println("========================================");
		System.out.println("The byte array serializable length is  : " + info.codeC().length);
	}
}

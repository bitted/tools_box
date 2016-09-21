/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.netty.lession601
 * 文件名：UserInfo.java
 * 版本信息：V1.0
 * 日期：2015-11-3-下午03:18:23
 * 作者：Administrator
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.util.netty.lession601;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * 类名称：UserInfo
 * 类描述：(java对象序列化性能比较)
 * 
 * 创建人：Administrator
 * 创建时间：2015-11-3 下午03:18:23
 * 修改人：
 * 修改时间：2015-11-3 下午03:18:23
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private int userID;

	public UserInfo buildUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public UserInfo buildUserID(int userID) {
		this.userID = userID;
		return this;
	}

	/**
	 * userName
	 * 
	 * @return the userName
	 * @since 1.0.0
	 */

	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * userID
	 * 
	 * @return the userID
	 * @since 1.0.0
	 */

	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public byte[] codeC() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		byte[] value = this.userName.getBytes();
		buffer.putInt(value.length);
		buffer.put(value);
		buffer.putInt(this.userID);
		buffer.flip();
		value = null;
		byte[] result = new byte[buffer.remaining()];
		buffer.get(result);
		return result;
	}

	public byte[] codeC(ByteBuffer buffer) {
		buffer.clear();
		byte[] value = this.userName.getBytes();
		buffer.putInt(value.length);
		buffer.put(value);
		buffer.putInt(this.userID);
		buffer.flip();
		value = null;
		byte[] result = new byte[buffer.remaining()];
		buffer.get(result);
		return result;
	}
}

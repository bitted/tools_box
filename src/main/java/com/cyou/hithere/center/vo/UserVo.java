package com.cyou.hithere.center.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UserVo {

	private String id;

	// 全名
	private String name;
	// 生日
	private String birthday;

	public String getId() {
		return id.toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

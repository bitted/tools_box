/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.hithere.center.domain
 * 文件名：Student.java
 * 版本信息：V1.0
 * 日期：2015-3-26-下午04:07:42
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 类名称：Teacher
 * 类描述：(Teacher)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-26 下午04:07:42
 * 修改人：
 * 修改时间：2015-3-26 下午04:07:42
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Entity(value = "t_teacher", queryNonPrimary = true, noClassnameStored = true)
public class Teacher implements Serializable {

	private static final long serialVersionUID = 5363121379328596690L;

	@Id
	private ObjectId id;

	private String name;

	private Integer age;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

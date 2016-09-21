/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.hithere.center.domain
 * 文件名：Course.java
 * 版本信息：V1.0
 * 日期：2015-3-26-下午04:11:14
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
 * 类名称：Course
 * 类描述：(课程)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-26 下午04:11:14
 * 修改人：
 * 修改时间：2015-3-26 下午04:11:14
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Entity(value = "t_course", queryNonPrimary = true, noClassnameStored = true)
public class Course implements Serializable {

	private static final long serialVersionUID = -891677607409610745L;
	@Id
	private ObjectId id;

	// 分数
	private Integer score;
	// 课程名称
	private String name;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.hithere.center.domain
 * 文件名：Book.java
 * 版本信息：V1.0
 * 日期：2015-3-30-下午05:16:37
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * 类名称：Book
 * 类描述：(描述此类所在架构中层次，如：某某服务接口实现、某某实体模型、某某工具类等----必填)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-30 下午05:16:37
 * 修改人：
 * 修改时间：2015-3-30 下午05:16:37
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Entity(value = "books2", noClassnameStored = true)
public class Book implements Serializable {

	private static final long serialVersionUID = -3688283712120256211L;
	@Id
	private ObjectId id;
	private String title;
	private String author;
	private Integer copies;
	private List<String> tags;

	public Book() {
	}

	public Book(final String title, final String author, final Integer copies, final String... tags) {
		this.title = title;
		this.author = author;
		this.copies = copies;
		this.tags = Arrays.asList(tags);
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

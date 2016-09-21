package com.cyou.hithere.center.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "t_user", queryNonPrimary = true, noClassnameStored = true)
public class User implements Serializable {

	private static final long serialVersionUID = -6337509846283517351L;

	@Id
	private ObjectId id;

	// 全名
	private String name;
	// 年龄
	private Integer age;
	// 老师
	private String tid;
	// 课程
	private String cid;
	// 生日
	private Date birthday;

	public String getId() {
		return id.toString();
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

	public Date getBirthday() {
		// birthday = DateUtil.parseDateMinuteFormat(DateUtil.getDateSecondFormat(birthday));
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

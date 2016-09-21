package com.cyou.hithere.center.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("sbtest1")
public class Sb {
	@Id
	private ObjectId id;

	private String k;
	private String c;
	private String pad;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getPad() {
		return pad;
	}

	public void setPad(String pad) {
		this.pad = pad;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

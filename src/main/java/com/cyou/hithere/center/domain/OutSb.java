package com.cyou.hithere.center.domain;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("out_sb")
public class OutSb {
	@Id
	private String k;
	private List<String> cs;
	private List<String> pads;


	public String getK() {
		return k;
	}


	public void setK(String k) {
		this.k = k;
	}


	public List<String> getCs() {
		return cs;
	}


	public void setCs(List<String> cs) {
		this.cs = cs;
	}


	public List<String> getPads() {
		return pads;
	}


	public void setPads(List<String> pads) {
		this.pads = pads;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

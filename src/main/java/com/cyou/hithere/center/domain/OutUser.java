package com.cyou.hithere.center.domain;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("out_user")
public class OutUser {
	@Id
	private String age;
	private List<String> names;
	private List<String> birthdays;


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public List<String> getNames() {
		return names;
	}


	public void setNames(List<String> names) {
		this.names = names;
	}


	public List<String> getBirthdays() {
		return birthdays;
	}


	public void setBirthdays(List<String> birthdays) {
		this.birthdays = birthdays;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

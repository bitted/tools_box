package com.cyou.hithere.center.service;

public interface UserService {

	public String save();

	public String update(String id, String name);

	public String list(String id);

	public String del(String id);

	public String aggregate();
}

package com.cyou.hithere.center.vo;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class BaseRet {

	private int code = 200;

	private Map<String, Object> data = new HashMap<String, Object>();

	public void putData(String key, Object value) {
		data.put(key, value);
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

	public static void main(String[] args) {
		BaseRet ret = new BaseRet();
		ret.putData("name", "lisi");
		System.out.println(ret.toString());
	}
}

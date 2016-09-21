package com.cyou.hithere.center.util;

public class PropertyTest {

	public static void main(String[] args) {
		Properties.init("1");
		Properties.setProperty("running", "true");
		String a = Properties.getProperty("a");
		System.out.println(a);
		Properties.setProperty("b", "bbbbbb");
		String b = Properties.getProperty("b");
		Properties.setProperty("running", "true");
//		Properties.init("1");

		System.out.println(b);
	}

}

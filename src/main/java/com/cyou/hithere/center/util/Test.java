package com.cyou.hithere.center.util;

import java.util.Random;

public class Test {

	/**
	 * main(这里用一句话描述这个方法的作用)
	 * 
	 * @param args
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */

	public static void main(String[] args) {
		Random r = new Random();
		System.out.println(r.nextInt(100));
		
		System.out.println(System.currentTimeMillis());
	}
}

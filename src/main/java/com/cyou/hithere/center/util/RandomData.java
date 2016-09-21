package com.cyou.hithere.center.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 类名称：RandomData
 * 类描述：(随机测试数据生成工具类.)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-28 下午02:45:45
 * 修改人：
 * 修改时间：2015-3-28 下午02:45:45
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class RandomData {

	private static Random random = new Random();

	/**
	 * 返回随机ID.
	 */
	public static long randomId() {
		return random.nextLong();
	}

	/**
	 * 返回随机名称, prefix字符串+5位随机数字.
	 */
	public static String randomName(String prefix) {
		return prefix + random.nextInt(10000);
	}

	/**
	 * 从输入list中随机返回一个对象.
	 */
	public static <T> T randomOne(List<T> list) {
		Collections.shuffle(list);
		return list.get(0);
	}

	/**
	 * 从输入list中随机返回n个对象.
	 */
	public static <T> List<T> randomSome(List<T> list, int n) {
		Collections.shuffle(list);
		return list.subList(0, n);
	}

	/**
	 * 从输入list中随机返回随机个对象.
	 */
	public static <T> List<T> randomSome(List<T> list) {
		int size = random.nextInt(list.size());
		if (size == 0) {
			size = 1;
		}
		return randomSome(list, size);
	}

	public static void main(String[] args) {
		System.out.println(randomId());
		System.out.println(randomName("userName"));

	}
}

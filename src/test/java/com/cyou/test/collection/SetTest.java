/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.test.collection
 * 文件名：SetTest.java
 * 版本信息：V1.0
 * 日期：2015-4-15-下午05:16:50
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.test.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * 类名称：SetTest
 * 类描述：(http://wankunde.iteye.com/blog/1504824)
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-15 下午05:16:50
 * 修改人：
 * 修改时间：2015-4-15 下午05:16:50
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class SetTest {
	static void opt() {
		Set<Integer> result = new HashSet<Integer>();
		Set<Integer> set1 = new HashSet<Integer>() {
			{
				add(1);
				add(3);
				add(5);
			}
		};

		Set<Integer> set2 = new HashSet<Integer>() {
			{
				add(1);
				add(2);
				add(3);
			}
		};

		result.clear();
		result.addAll(set1);
		result.retainAll(set2);
		System.out.println("交集：" + result);

		result.clear();
		result.addAll(set1);
		result.removeAll(set2);
		System.out.println("差集：" + result);

		result.clear();
		result.addAll(set1);
		result.addAll(set2);
		System.out.println("并集：" + result);
	}

	/**
	 * main(java求“交、叉、并集)
	 * 
	 * @param args
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */

	public static void main(String[] args) {
		opt();
	}

}

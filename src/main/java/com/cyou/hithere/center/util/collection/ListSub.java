/**
 * 项目名称：hithere-center
 * 包名：com.cyou.hithere.center.util.collection
 * 文件名：ListSub.java
 * 版本信息：V1.0
 * 日期：2016年5月25日-上午10:46:03
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类名称：ListSub
 * 类描述：(描述此类所在架构中层次)
 *
 * 创建人：litaijun
 * 创建时间：2016年5月25日 上午10:46:03
 * 修改人：
 * 修改时间：2016年5月25日 上午10:46:03
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class ListSub {
	/**
	 * 拆分集合
	 * 
	 * @param <T>
	 * @param resList
	 *            要拆分的集合
	 * @param count
	 *            每个集合的元素个数
	 * @return 返回拆分后的各个集合
	 */
	public static <T> List<List<T>> split(List<T> resList, int count) {

		if (resList == null || count < 1)
			return null;
		List<List<T>> ret = new ArrayList<List<T>>();
		int size = resList.size();
		if (size <= count) { // 数据量不足count指定的大小
			ret.add(resList);
		} else {
			int pre = size / count;
			int last = size % count;
			// 前面pre个集合，每个大小都是count个元素
			for (int i = 0; i < pre; i++) {
				List<T> itemList = new ArrayList<T>();
				for (int j = 0; j < count; j++) {
					itemList.add(resList.get(i * count + j));
				}
				ret.add(itemList);
			}
			// last的进行处理
			if (last > 0) {
				List<T> itemList = new ArrayList<T>();
				for (int i = 0; i < last; i++) {
					itemList.add(resList.get(pre * count + i));
				}
				ret.add(itemList);
			}
		}
		return ret;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> resList = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33",
				"34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52",
				"53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71",
				"72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90",
				"91", "92", "93", "94", "95", "96", "97", "98", "99");
		List<List<String>> ret = split(resList, 10);

		for (int i = 0; i < ret.size(); i++) {
			System.out.println(ret.get(i));
		}

	}

}

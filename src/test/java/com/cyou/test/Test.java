package com.cyou.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List list1 = new ArrayList();
		list1.add("1111");
		list1.add("2222");
		list1.add("3333");

		List list2 = new ArrayList();
		list2.add("3333");
		list2.add("4444");
		list2.add("5555");

		// 并集
//		list1.addAll(list2);
		// 交集
//		list1.retainAll(list2);
		// 差集
//		list1.removeAll(list2);
		// 无重复并集
//		list2.removeAll(list1);
//		list1.addAll(list2);

		Iterator<String> it = list1.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());

		}

		// System.out.println("-----------------------------------\n");
//		printStr(list1);

	}

	public static void printStr(List list1) {
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i));
		}
	}
}

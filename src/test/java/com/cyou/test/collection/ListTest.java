/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.test.collection
 * 文件名：ListTest.java
 * 版本信息：V1.0
 * 日期：2015-4-15-下午03:53:52
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.test.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

/**
 * 类名称：ListTest
 * 类描述：(演示List接口)
 * 因为List采用数组方式存储数据，可以通过索引查找数据，所以插入查询很快，插入和删除数据由于涉及到数组元素移动等内存操作，
 * 所以不合适，Vector由于使用了synchronized方法（线程安全）所以性能上比ArrayList要差，LinkedList使用双向链表实现存储，
 * 按序号索引数据需要进行向前或向后遍历，但是插入数据只需记录本项的前后项即可，所以插入速度较快。
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-15 下午03:53:52
 * 修改人：
 * 修改时间：2015-4-15 下午03:53:52
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class ListTest {

	/**
	 * opt(集合中交、并、差集)
	 * 
	 * 返回类型：void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	static void opt() {
		List<String> result = new ArrayList<String>();

		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		List<String> list2 = new ArrayList<String>();
		list2.add("1");
		list2.add("3");
		list2.add("5");

		result.addAll(list);
		result.removeAll(list2);
		System.out.println("差集：" + result);
		result.clear();
		result.addAll(list);
		result.retainAll(list2);
		System.out.println("交集：" + result);
		result.clear();
		result.addAll(list);
		result.addAll(list2);
		Set<String> set = new HashSet<String>();
		set.addAll(result);
		System.out.println("并集：" + set);
	}

	/**
	 * testVector(Vector非常类似ArrayList，但是Vector是同步的。由Vector创建的Iterator，虽然和ArrayList创
	 * 建的Iterator是同一接口，但是，因为Vector是同步的，当一个Iterator被创建而且正在被使用，另一个线程改变了Vector的状态
	 * （例如，添加或删除了一些元素），这时调用Iterator的方法时将抛出ConcurrentModificationException，因此必须捕 获该异常。 )
	 * 
	 * 返回类型：void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	static void testVector() {
		Vector<String> v = new Vector<String>();
		v.add("1");
		v.add("2");
		v.add("3");
		System.out.println(v);
		Vector<String> v2 = (Vector<String>) v.clone();
		System.out.println(v2);
		v.setElementAt("3", 0);
		System.out.println(v);
	}

	static void testStack() {
		Stack<String> s = new Stack<String>();
		s.add("1");
		s.add("2");
		s.add("3");
		System.out.println(s);
		s.setElementAt("3", 0);
		System.out.println(s);
		s.push("4");
		System.out.println(s);
		s.peek();
		System.out.println(s);
		s.pop();
		System.out.println(s);
		s.setSize(10);
		s.insertElementAt("6", 6);
		System.out.println(s.size());
		System.out.println(s);
	}

	static void testLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add(null);

		System.out.println("添加：" + list);
		list.add(1, "2");
		System.out.println("插入：" + list);
		// list.remove("2");
		// list.remove(2);
		System.out.println("删除：" + list);
		System.out.println("包含：" + list.contains("2"));
		Iterator<String> iterator = list.iterator();
		System.out.println("==Iterator遍历==");
		while (iterator.hasNext()) {
			String object = iterator.next();
			System.out.print(" " + object);
		}
		System.out.println("\n==get==");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(" " + list.get(i));
		}
		list.remove("2");
		System.out.println("\n==remove==" + list);
		list.add("6");
		System.out.println("\n==add==" + list);

		System.out.println("\n==peek==" + list.peek() + " peekFirst:" + list.peekFirst() + " element：" + list.element()
				+ " peekLast:" + list.peekLast() + " getLast:" + list.getLast() + " poll【poll会剔除数据】:" + list.poll()
				+ " pollFirst:" + list.pollFirst() + " pollLast:" + list.pollLast() + " pop:" + list.pop());
		list.addFirst("7");
		list.addLast("8");
		list.set(1, "10");
		System.out.println("\n==list==" + list);

	}

	static void testList() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		System.out.println("添加：" + list);
		list.add(1, "2");
		System.out.println("插入：" + list);
		// list.remove("2");
		list.remove(2);
		System.out.println("删除：" + list);
		System.out.println("包含：" + list.contains("2"));
		Iterator<String> iterator = list.iterator();
		System.out.println("==Iterator遍历==");
		while (iterator.hasNext()) {
			String object = iterator.next();
			System.out.print(" " + object);
		}

		/**
		 * hasPrevious必须在hasNext执行完才可以使用，因为要是hasNext没有执行下标还是在第一个位置，
		 * 不可能进入hasPrevious中
		 */
		System.out.println("==ListIterator next遍历==");
		ListIterator<String> iter = list.listIterator();
		while (iter.hasNext()) {
			System.out.print(" " + iter.next());
		}

		System.out.println("\n==ListIterator pre遍历==");
		while (iter.hasPrevious()) {
			System.out.print(" " + iter.previous());
		}
	}

	public static void main(String[] args) {
		// testList();
		// testLinkedList();
		// opt();
		// testVector();
		testStack();
	}
}

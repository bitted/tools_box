package com.cyou.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 类名称：StringBuilderTester
 * 类描述：(String,StringBuilder与StringBuffer的区别)
 * 
 * 
 * <pre>
 * 从上面的结果来看，这三个类在单线程程序中的性能差别一目了然，采用String对象时，即使运行次数仅是采用其他对象的1/100，
 * 其执行时间仍然比其他对象高出25倍以上；而采用StringBuffer对象和采用StringBuilder对象的差别也比较明显，前者是后者的1.5倍
 * 左右。由此可见，如果我们的程序是在单线程下运行，或者是不必考虑到线程同步问题，我们应该优先使用StringBuilder类；当然，如
 * 果要保证线程安全，自然非StringBuffer莫属了。
 * 
 * 除了对多线程的支持不一样外，这两个类的使用几乎没有任何差别，上面的例子就是个很好的说明。appendItemsToStringBuiler和
 * appendItemsToStirngBuffer两个方法除了采用的对象分别为StringBuilder和StringBuffer外，其他完全相同，而效果也完全相同。
 * </pre>
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-13 下午05:03:23
 * 修改人：
 * 修改时间：2015-4-13 下午05:03:23
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class StringBuilderTester {
	private static final String base = " base string. ";
	private static final int count = 2000000;

	public static void stringTest() {
		long begin, end;
		begin = System.currentTimeMillis();
		String test = new String(base);
		for (int i = 0; i < count / 100; i++) {
			test = test + " add ";
		}
		end = System.currentTimeMillis();
		System.out.println((end - begin) + " millis has elapsed when used String. ");
	}

	public static void stringBufferTest() {
		long begin, end;
		begin = System.currentTimeMillis();
		StringBuffer test = new StringBuffer(base);
		for (int i = 0; i < count; i++) {
			test = test.append(" add ");
		}
		end = System.currentTimeMillis();
		System.out.println((end - begin) + " millis has elapsed when used StringBuffer. ");
	}

	public static void stringBuilderTest() {
		long begin, end;
		begin = System.currentTimeMillis();
		StringBuilder test = new StringBuilder(base);
		for (int i = 0; i < count; i++) {
			test = test.append(" add ");
		}
		end = System.currentTimeMillis();
		System.out.println((end - begin) + " millis has elapsed when used StringBuilder. ");
	}

	public static String appendItemsToStringBuiler(List list) {
		StringBuilder b = new StringBuilder();

		for (Iterator i = list.iterator(); i.hasNext();) {
			b.append(i.next()).append(" ");
		}

		return b.toString();
	}

	public static void addToStringBuilder() {
		List<String> list = new ArrayList<String>();
		list.add(" I ");
		list.add(" play ");
		list.add(" Bourgeois ");
		list.add(" guitars ");
		list.add(" and ");
		list.add(" Huber ");
		list.add(" banjos ");

		System.out.println(StringBuilderTester.appendItemsToStirngBuffer(list));
	}

	public static String appendItemsToStirngBuffer(List<String> list) {
		StringBuffer b = new StringBuffer();

		for (Iterator<String> i = list.iterator(); i.hasNext();) {
			b.append(i.next()).append(" ");
		}

		return b.toString();
	}

	public static void addToStringBuffer() {
		List<String> list = new ArrayList<String>();
		list.add(" I ");
		list.add(" play ");
		list.add(" Bourgeois ");
		list.add(" guitars ");
		list.add(" and ");
		list.add(" Huber ");
		list.add(" banjos ");

		System.out.println(StringBuilderTester.appendItemsToStirngBuffer(list));
	}

	public static void main(String[] args) {
		stringTest();
		stringBufferTest();
		stringBuilderTest();
		addToStringBuffer();
		addToStringBuilder();
	}
}

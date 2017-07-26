/**
 * 项目名称：tools_box
 * 包名：com.cyou.hithere.center.util.linklist
 * 文件名：LinkList.java
 * 版本信息：V1.0
 * 日期：2016年12月20日-下午3:26:25
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.linklist;

import java.util.Scanner;
import java.util.Stack;

/**
 * 类名称：LinkList
 * 类描述：(链表翻转的方法)
 *
 * 创建人：litaijun
 * 创建时间：2016年12月20日 下午3:26:25
 * 修改人：
 * 修改时间：2016年12月20日 下午3:26:25
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class LinkList {
	public Node head;

	/**
	 * createLinkList(创建一个链表)
	 * 
	 * @param x
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	public void createLinkList(int[] x) {

		System.out.println("创建链表数组---->");
		Node pnew;// 定义一个新的节点
		Node ptail = new Node();
		head = ptail;
		for (int i = 0; i < x.length; i++) {
			pnew = new Node();
			pnew.setData(x[i]);
			ptail.setNext(pnew);
			ptail = pnew;
		}
	}

	/**
	 * displayLinkList(正序输出链表的所有内容)
	 * 
	 * 返回类型：void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public void displayLinkList() {
		// System.out.println("顺序输出链表数组---->");
		Node node = head.getNext();
		while (node != null) {
			System.out.print(node.getData() + "--->");
			node = node.getNext();
		}
		System.out.println("null");
	}

	/**
	 * reverseLinkList(逆序输出链表的所有的内容)
	 * 
	 * 返回类型：void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public void reverseLinkList() {
		// System.out.println("逆序处理链表数组---->");
		if (head == null || head.getNext() == null) {
			// 当链表只有一个头节点或者只有一个节点，逆序还是原来的链表，所有直接返回。
			return;
		} else {
			Node p = head.getNext();
			Node q = head.getNext().getNext();
			p.setNext(null);// 将第一个节点的next置为空，是否会出现环
			Node temp = null;
			while (q != null) {
				temp = q.getNext();
				q.setNext(p);
				p = q;
				q = temp;
			}
			if (q == null) {
				head.setNext(p);
				q = null;
			}
		}
	}

	/**
	 * reverseLinkList_Stack(再介绍比较简单的b方法)
	 * 借助栈先进后出的特性，将链表存入栈中，然后出栈，刚好就实现了链表的逆序输出(此方法只实现了逆序输出，但并没有将链表逆序)
	 * 返回类型：void
	 * 
	 * @exception
	 * @since 1.0.0
	 */
	public void reverseLinkList_Stack() {
		Stack<Node> stack = new Stack<Node>();
		Node node = head.getNext();
		while (node != null) {
			stack.push(node);
			node = node.getNext();
		}
		while (stack.size() > 0) {
			node = stack.pop();
			// 堆栈特性逆序
			System.out.print(node.getData() + "--->");
		}
		System.out.println("null");
	}

	/**
	 * main(这里用一句话描述这个方法的作用)
	 * 
	 * @param args
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		LinkList linkList = new LinkList();
		Scanner input = new Scanner(System.in);
		System.out.println("输入链表个数：");
		int n = input.nextInt();
		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = i;
		}
		linkList.createLinkList(x);
		linkList.displayLinkList();
		linkList.reverseLinkList();
		linkList.displayLinkList();
		linkList.reverseLinkList_Stack();
	}
}

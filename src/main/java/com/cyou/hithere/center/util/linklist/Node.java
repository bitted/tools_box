/**
 * 项目名称：tools_box
 * 包名：com.cyou.hithere.center.util.linklist
 * 文件名：Node.java
 * 版本信息：V1.0
 * 日期：2016年12月20日-下午3:22:42
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.linklist;

/**
 * 类名称：Node
 * 类描述：( 数据结构单向链表逆序反转)
 * URL:http://blog.csdn.net/xingzhemoluo/article/details/40897053
 * 将单向链表逆序输出，方法有三种：
 * a.遍历链表，将每个节点的内容存入一个数组中，然后逆序输出数组(最简单的做法)
 * b.使用栈来逆序输出
 * c.直接将链表逆序然后输出
 * 先介绍c方法：
 * 1). 若链表为空或只有一个元素，则直接返回；
 * 2). 设置两个前后相邻的指针p,q. 将p所指向的节点作为q指向节点的后继；
 * 3). 重复2),直到q为空
 * 4). 调整链表头和链表尾
 * 
 * 创建人：litaijun
 * 创建时间：2016年12月20日 下午3:22:42
 * 修改人：
 * 修改时间：2016年12月20日 下午3:22:42
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class Node {

	private int data;
	private Node next;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}

/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.test.designmode
 * 文件名：Client.java
 * 版本信息：V1.0
 * 日期：2015-4-14-下午02:55:08
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.test.designmode;

/**
 * 类名称：Client
 * 类描述：(工程设计模式)
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-14 下午02:55:08
 * 修改人：
 * 修改时间：2015-4-14 下午02:55:08
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class Client {

	public static void main(String[] args) {
		IFactory factory = new Factory();
		IProduct product = factory.createProduct();
		product.productMethod();

	}
}

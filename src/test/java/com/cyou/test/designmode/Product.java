/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.test.designmode
 * 文件名：Product.java
 * 版本信息：V1.0
 * 日期：2015-4-14-下午03:00:40
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.test.designmode;

/**
 * 类名称：Product
 * 类描述：(产品实现)
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-14 下午03:00:40
 * 修改人：
 * 修改时间：2015-4-14 下午03:00:40
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class Product implements IProduct {

	@Override
	public void productMethod() {
		System.out.println("产品");
	}

}

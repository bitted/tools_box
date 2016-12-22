/**
 * 项目名称：tools_box
 * 包名：com.cyou.hithere.center.util.math
 * 文件名：BigInteger.java
 * 版本信息：V1.0
 * 日期：2016年12月22日-下午12:40:33
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.math;

import java.math.BigInteger;

/**
 * 类名称：BigInteger
 * 类描述：(java实现的百亿级别计算器)
 *
 * 创建人：litaijun
 * 创建时间：2016年12月22日 下午12:40:33
 * 修改人：
 * 修改时间：2016年12月22日 下午12:40:33
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class BigIntegerTest {

	/**
	 * add(加法)
	 * 
	 * @param x
	 * @param y
	 * @return
	 *         返回类型：BigInteger
	 * @exception
	 * @since 1.0.0
	 */
	public static BigInteger add(BigInteger x, BigInteger y) {
		return x.add(y);
	}

	/**
	 * subtract(减法)
	 * 
	 * @param x
	 * @param y
	 * @return
	 *         返回类型：BigInteger
	 * @exception
	 * @since 1.0.0
	 */
	public static BigInteger subtract(BigInteger x, BigInteger y) {
		return x.subtract(y);
	}

	/**
	 * multiply(乘法)
	 * 
	 * @param x
	 * @param y
	 * @return
	 *         返回类型：BigInteger
	 * @exception
	 * @since 1.0.0
	 */
	public static BigInteger multiply(BigInteger x, BigInteger y) {
		return x.multiply(y);
	}

	/**
	 * divide(除法)
	 * 
	 * @param x
	 * @param y
	 * @return
	 *         返回类型：BigInteger
	 * @exception
	 * @since 1.0.0
	 */
	public static BigInteger divide(BigInteger x, BigInteger y) {
		return x.divide(y);
	}

	public static void main(String[] args) {
		double x = Math.pow(2, 64);
		BigInteger xb = BigInteger.valueOf((long) x);
		double y = Math.pow(2, 64);
		BigInteger yb = BigInteger.valueOf((long) y);
		System.out.println("xb=" + xb);
		System.out.println("x+y=" + add(xb, yb));
		System.out.println("x-y=" + subtract(xb, yb));
		System.out.println("x*y=" + multiply(xb, yb));
		System.out.println("x/y=" + divide(xb, yb));
	}

}

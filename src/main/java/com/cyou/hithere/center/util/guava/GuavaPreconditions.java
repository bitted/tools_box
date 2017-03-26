/**
 * 
 */
package com.cyou.hithere.center.util.guava;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * @author Administrator
 *
 */
public class GuavaPreconditions implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -974814238024373548L;

	/**
	 * 测试Preconditions的方法，如果返回值与期望值不符，抛出响应的异常，可以应用在项目中判空和返回值的判断。
	 */
	public static void testPreconditions() {
		BigDecimal b = new BigDecimal(1);
		Preconditions.checkArgument(b.compareTo(BigDecimal.ZERO) > 0, "交易金额必须大于0:%s", b);
		Preconditions.checkNotNull(b);
		Preconditions.checkElementIndex(1, 2);
		Preconditions.checkNotNull("123", "用户id不能为空！");
		Preconditions.checkState(true);
		Preconditions.checkArgument(true, "请求数据为空！");
	}

	/**
	 * 
	 */
	public static void testOptional() {
		BigDecimal b = new BigDecimal(1);
		Optional<Integer> p1 = Optional.of(5);
		System.out.println(p1.isPresent());
		System.out.println(p1.get());
		Optional<BigDecimal> p2 = Optional.fromNullable(b);
		System.out.println(p2.isPresent() + " " + p2.get() + " " + p2.or(new BigDecimal(2)) + " ");
	}

	public static void main(String[] args) {
		// testPreconditions();
		testOptional();
	}

}

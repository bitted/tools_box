package com.cyou.hithere.center.util;




public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * isEmpty(判断多个字段是否为空)
	 * 
	 * @param param
	 * @return
	 *         返回类型：boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isEmpty(String... param) {
		for (String str : param) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(org.apache.commons.lang.StringUtils.containsIgnoreCase("Aaabbb", "aaa"));
		System.out.println(org.apache.commons.lang.StringUtils.indexOfIgnoreCase("Aaabbb", "aaa"));
		System.out.println(org.apache.commons.lang.StringUtils.leftPad("1", 3, '0'));
		System.out.println(org.apache.commons.lang.StringUtils.leftPad("12", 3, '0'));
		System.out.println(org.apache.commons.lang.StringUtils.abbreviate("litaij", 6));
		System.out.println(org.apache.commons.lang.StringUtils.abbreviate("abcdefghijk", 5));
		System.out.println(org.apache.commons.lang.StringUtils.capitalize("abc"));
		System.out.println(org.apache.commons.lang.StringUtils.uncapitalize("ABc"));
		System.out.println( org.apache.commons.lang.StringUtils.abbreviate("aaaabcdddd", 4, 6) );
	}
}

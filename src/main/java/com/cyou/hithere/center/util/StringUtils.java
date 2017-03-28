package com.cyou.hithere.center.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private static final Pattern KVP_PATTERN = Pattern.compile("([_.a-zA-Z0-9][-_.a-zA-Z0-9]*)[=](.*)"); // key
																											// value
	private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");

	/**
	 * isEmpty(判断多个字段是否为空)
	 * 
	 * @param param
	 * @return 返回类型：boolean
	 * @exception @since
	 *                1.0.0
	 */
	public static boolean isEmpty(String... param) {
		for (String str : param) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否Integer类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (str == null || str.length() == 0)
			return false;
		return INT_PATTERN.matcher(str).matches();
	}

	/**
	 * parse key-value pair.
	 * 
	 * @param str
	 *            string.
	 * @param itemSeparator
	 *            item separator.
	 * @return key-value map;
	 */
	private static Map<String, String> parseKeyValuePair(String str, String itemSeparator) {
		String[] tmp = str.split(itemSeparator);
		Map<String, String> map = new HashMap<String, String>(tmp.length);
		for (int i = 0; i < tmp.length; i++) {
			Matcher matcher = KVP_PATTERN.matcher(tmp[i]);
			if (matcher.matches() == false)
				continue;
			map.put(matcher.group(1), matcher.group(2));
		}
		return map;
	}

	/**
	 * 获取key对应的map中的value
	 * 
	 * @param qs
	 * @param key
	 * @return
	 */
	public static String getQueryStringValue(String qs, String key) {
		Map<String, String> map = StringUtils.parseQueryString(qs);
		return map.get(key);
	}

	/**
	 * parse query string to Parameters.
	 * 
	 * @param qs
	 *            query string.
	 * @return Parameters instance.
	 */
	public static Map<String, String> parseQueryString(String qs) {
		if (qs == null || qs.length() == 0)
			return new HashMap<String, String>();
		return parseKeyValuePair(qs, "\\&");
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
		System.out.println(org.apache.commons.lang.StringUtils.abbreviate("aaaabcdddd", 4, 6));
	}
}

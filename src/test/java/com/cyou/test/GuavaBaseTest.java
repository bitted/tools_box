/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.test
 * 文件名：GuavaTest.java
 * 版本信息：V1.0
 * 日期：2015-4-9-下午01:46:30
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.test;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import java.util.Collections;
import java.util.Set;

import junit.framework.TestCase;

import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Converter;
import com.google.common.base.Defaults;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

/**
 * 类名称：GuavaTest
 * 类描述：(演示guava中base工具集方法使用)
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-9 下午01:46:30
 * 修改人：
 * 修改时间：2015-4-9 下午01:46:30
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class GuavaBaseTest extends TestCase {
	public void testTruncate() {
		// System.out.println(Ascii.truncate("foobar", 3, "..."));
		assertEquals("foobar", Ascii.truncate("foobar", 10, "..."));
		assertEquals("fo...", Ascii.truncate("foobar", 5, "..."));
		assertEquals("foobar", Ascii.truncate("foobar", 6, "..."));
		assertEquals("...", Ascii.truncate("foobar", 3, "..."));
		assertEquals("foobar", Ascii.truncate("foobar", 10, "…"));
		assertEquals("foo…", Ascii.truncate("foobar", 4, "…"));
		assertEquals("foobar", Ascii.truncate("foobar", 6, "…"));
		assertEquals("foob…", Ascii.truncate("foobar", 5, "…"));
		assertEquals("foo", Ascii.truncate("foobar", 3, ""));
	}

	public void testCaseFormat() {
		System.out.println(LOWER_HYPHEN.to(LOWER_UNDERSCORE, "foo-bar"));
		System.out.println(LOWER_HYPHEN.to(LOWER_CAMEL, "foo-bar"));
		System.out.println(LOWER_HYPHEN.to(UPPER_CAMEL, "foo-bar"));
		System.out.println(LOWER_HYPHEN.to(UPPER_UNDERSCORE, "foo-bar"));
		System.out.println(LOWER_CAMEL.to(LOWER_HYPHEN, "HTTP"));
	}

	public void testCharMatcher() {
		// System.out.println(CharMatcher.anyOf("abc"));
		// System.out.println(CharMatcher.is('a'));
		// System.out.println(inRange('n', 'q'));
	}

	private static final Converter<String, Long> STR_TO_LONG = new Converter<String, Long>() {
		@Override
		public Long doForward(String object) {
			return Long.valueOf(object);
		}

		@Override
		public String doBackward(Long object) {
			return String.valueOf(object);
		}

		@Override
		public String toString() {
			return "string2long";
		}
	};

	public void testConverter() {
		final Long LONG_VAL = 12345L;
		final String STR_VAL = "12345";

		final ImmutableList<String> STRINGS = ImmutableList.of("123", "456");
		final ImmutableList<Long> LONGS = ImmutableList.of(123L, 456L);
		assertEquals(LONG_VAL, STR_TO_LONG.convert(STR_VAL));
		System.out.println(STR_TO_LONG.convert(STR_VAL));
		System.out.println(STR_TO_LONG.reverse().convert(LONG_VAL));
		Iterable<Long> convertedValues = STR_TO_LONG.convertAll(STRINGS);
		assertEquals(LONGS, ImmutableList.copyOf(convertedValues));
		System.out.println();
		System.out.println();
	}

	public void testGetDefaultValue() {
		assertEquals(false, Defaults.defaultValue(boolean.class).booleanValue());
		assertEquals('\0', Defaults.defaultValue(char.class).charValue());
		assertEquals(0, Defaults.defaultValue(byte.class).byteValue());
		assertEquals(0, Defaults.defaultValue(short.class).shortValue());
		assertEquals(0, Defaults.defaultValue(int.class).intValue());
		assertEquals(0, Defaults.defaultValue(long.class).longValue());
		assertEquals(0.0f, Defaults.defaultValue(float.class).floatValue());
		assertEquals(0.0d, Defaults.defaultValue(double.class).doubleValue());
		assertNull(Defaults.defaultValue(void.class));
		assertNull(Defaults.defaultValue(String.class));
	}

	public void testObjects() throws Exception {
		System.out.println(Objects.equal("a", "a"));
		System.out.println(Objects.hashCode(1, 2, 3) == Objects.hashCode(3, 2, 1));
		assertTrue(Objects.equal(1, 1));

		String s1 = "foobar";
		String s2 = new String(s1);
		assertTrue(Objects.equal(s1, s2));

		assertFalse(Objects.equal(s1, null));
		assertFalse(Objects.equal(null, s1));
		assertFalse(Objects.equal("foo", "bar"));
		assertFalse(Objects.equal("1", 1));
	}

	public void testOptional() throws Exception {
		System.out.println(Optional.of("training").get());
		System.out.println(Optional.of("a").or("default"));

		Set<String> expected = Collections.singleton("a");
		assertEquals(expected, Optional.of("a").asSet());
	}

	public void testSplitter() throws Exception {
		System.out.println(Splitter.on(',').splitToList("a,b,c"));
		System.out.println(Splitter.on(',').split("a,b,c"));
		System.out.println(Splitter.on('.').split("a,b,c"));
		System.out.println(Splitter.on(CharMatcher.WHITESPACE).split("Testing\nrocks\tDebugging sucks"));
		System.out.println(Splitter.on('.').omitEmptyStrings().split("a..b.c"));
		System.out.println(Splitter.on('.').trimResults().split("a..c"));
		System.out.println(Splitter.on('.').omitEmptyStrings().trimResults().split("a..c"));
		System.out.println(Splitter.fixedLength(2).split("abcdef"));
		System.out.println(Splitter.fixedLength(1).limit(2).split("abcdef"));
		System.out.println(Splitter.on(',').withKeyValueSeparator(":").split("a:1,b:2,c:3"));
	}

	public void testStrings() throws Exception {
		System.out.println(Strings.nullToEmpty(null));
		System.out.println(Strings.emptyToNull(null));
		System.out.println(Strings.isNullOrEmpty(null));
		System.out.println(Strings.padStart("7", 3, '0'));
		System.out.println(Strings.padStart("xx", 3, '-'));
		System.out.println(Strings.padEnd("xx", 3, '-'));
		System.out.println(Strings.repeat("20", 2));
		System.out.println(Strings.commonPrefix("aab", "aaaabca"));// startwith
		System.out.println(Strings.commonSuffix(new StringBuffer("xyzabc"), "xxxabc"));// endwith
	}
}

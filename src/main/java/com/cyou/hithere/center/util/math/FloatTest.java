/**
 * @title FloatTest
 * @package com.cyou.hithere.center.util.math
 * @description: 简理财
 * @copyright: Copyright (c) 2017
 * @company:北京简便快乐信息技术有限公司
 * @author litaijun
 * @date 2017/7/26 10:59
 */
package com.cyou.hithere.center.util.math;

/**
 * (类描述：写一个Java程序，将100.2转成IEEE745 二进制表示 ，给出程序和结果。)
 *
 * @author litaijun
 * @create 2017/7/26 10:59
 */
public class FloatTest {

    public static String convert(float num) {
        int value = Float.floatToIntBits(num);
        return value > 0 ? "0" + Integer.toBinaryString(value) : Integer.toBinaryString(value);
    }

    public static void main(String[] args) {
        String val = convert(100.2f);
        System.out.println("val = [" + val + "]");
    }
}
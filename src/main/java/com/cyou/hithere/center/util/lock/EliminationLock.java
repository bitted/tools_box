/**
 * @title Lock2
 * @package com.cyou.hithere.center.util.lock
 * @description: 简理财
 * @copyright: Copyright (c) 2017
 * @company:北京简便快乐信息技术有限公司
 * @author litaijun
 * @date 2017/7/25 16:22
 */
package com.cyou.hithere.center.util.lock;

/**
 * (类描述：锁消除)
 *
 * @author litaijun
 * @create 2017/7/25 16:22
 */
public class EliminationLock {
    static int CIRCLE= 2000000;
//    -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks 执行craeteStringBuffer消耗时间: 182 ms
    public static void main(String args[]) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            craeteStringBuffer("JVM", "Diagnosis");
        }
        long bufferCost = System.currentTimeMillis() - start;
        System.out.println("craeteStringBuffer: " + bufferCost + " ms");
    }

    public static String craeteStringBuffer(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }
}
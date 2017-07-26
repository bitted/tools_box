/**
 * @title PartialLock
 * @package com.cyou.hithere.center.util.lock
 * @description: 简理财
 * @copyright: Copyright (c) 2017
 * @company:北京简便快乐信息技术有限公司
 * @author litaijun
 * @date 2017/7/25 16:17
 */
package com.cyou.hithere.center.util.lock;

import java.util.List;
import java.util.Vector;

/**
 * (类描述：偏向锁)
 *
 * @author litaijun
 * @create 2017/7/25 16:17
 */
public class PartialLock {
    public static List<Integer> numberList = new Vector<Integer>();
//-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
            System.out.println(startnum + "==" + count);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
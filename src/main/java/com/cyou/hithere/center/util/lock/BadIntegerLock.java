/**
 * @title BadIntegerLock
 * @package com.cyou.hithere.center.util.lock
 * @description: 简理财
 * @copyright: Copyright (c) 2017
 * @company:北京简便快乐信息技术有限公司
 * @author litaijun
 * @date 2017/7/25 16:16
 */
package com.cyou.hithere.center.util.lock;

/**
 * (类描述：一个错误使用锁的案例)
 *
 * @author litaijun
 * @create 2017/7/25 16:16
 */
public class BadIntegerLock {
    static Integer i = 0;

    public static class AddThread extends Thread {
        public void run() {
            for (int k = 0; k < 100000; k++) {
                synchronized (i) {
                    i++;
                    System.out.println(" i= " + i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread t1 = new AddThread();
        AddThread t2 = new AddThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
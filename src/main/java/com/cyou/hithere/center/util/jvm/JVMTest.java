/**
 * @title JVMTest
 * @package com.cyou.hithere.center.util.jvm
 * @description: 简理财
 * @copyright: Copyright (c) 2017
 * @company:北京简便快乐信息技术有限公司
 * @author litaijun
 * @date 2017/7/19 15:14
 */
package com.cyou.hithere.center.util.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * (类描述：jvm基础)
 *
 * @author litaijun
 * @create 2017/7/19 15:14
 */
public class JVMTest {
    /**
     * @description (强引用  是JAVA 中默认采用的一种方式，我们平时创建的引用都属于强引用。如果一个对象没有强引用，那么对象就会被回收。)
     * @param
     * @return void
     * @author litaijun
     * @date 2017/7/19 15:18
     */
    public static void testStrongReference() {

        Object referent = new Object();
        System.out.println("referent1:" + referent);

        Object strongReference = referent;

        referent = null;

        System.gc();

        System.out.println("referent2:" + referent);
        System.out.println("strongReference:" + strongReference);
    }
    /**
     * @description (软引用  的对象在 GC 的时候不会被回收，只有当内存不够用的时候才会真正
     *       的回收，因此软引用适合缓存的场合，这样使得缓存中的对象可以尽量的再内存
     *       中待长久一点。)
     * @param
     * @return void
     * @author litaijun
     * @date 2017/7/19 15:21
     */
    public static void testSoftReference() {
        String str = "test";
        SoftReference<String> softreference = new SoftReference<String>(str);
        System.out.println("softreference:" + softreference.get());

        str = null;

        System.gc();

        System.out.println("softreference:" + softreference.get());
    }
    /**
    * @description (弱引用有利于对象更快的被回收，假如一个对象没有强引用只有弱引用，
    * 那么在 GC 后，这个对象肯定会被回收。)
    * @param
    * @return
    * @author litaijun
    * @date 2017/7/19 15:27
    */
    public static void testWeakReference() {
        String str = "test";
        WeakReference<String> weakReference = new WeakReference<String>(str);
        System.out.println("weakReference:" + weakReference.get());

        str = null;

        System.gc();

        System.out.println("weakReference:" + weakReference.get());
    }

    public static void main(String[] args) {
//        testStrongReference();
//        testSoftReference();
        testWeakReference();
    }
}
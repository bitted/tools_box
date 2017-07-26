/**
 * @title Overflow
 * @package com.cyou.hithere.center.util.jvm
 * @description: 简理财
 * @copyright: Copyright (c) 2017
 * @company:北京简便快乐信息技术有限公司
 * @author litaijun
 * @date 2017/7/25 16:55
 */
package com.cyou.hithere.center.util.jvm;

/**
 * (类描述：模拟栈溢出情况，提出栈溢出解决方法：减少栈贞的空间很必要or增大线程的栈的大小。)
 * @author litaijun
 * @create 2017/7/25 16:55
 */
public class Overflow {
    private volatile int a = 1;
    private volatile int b = 2;
    private volatile int c = 3;

//    private  int a = 1;
//    private  int b = 2;
//    private  int c = 3;
//    设置栈大小： -Xss128k
    public static void main(String[] args) {
        Overflow of = new Overflow();
        try {
            of.recursion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recursion(){
        ++a;
        ++b;
        ++c;
        recursion();
    }
}
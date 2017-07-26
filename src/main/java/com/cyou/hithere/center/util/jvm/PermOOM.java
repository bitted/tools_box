/**
 * @title PermOOM
 * @package com.cyou.hithere.center.util.jvm
 * @description: 简理财
 * @copyright: Copyright (c) 2017
 * @company:北京简便快乐信息技术有限公司
 * @author litaijun
 * @date 2017/7/25 16:45
 */
package com.cyou.hithere.center.util.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * (类描述：抛出由于Perm区溢出引起的OOM)
 * @author litaijun
 * @create 2017/7/25 16:45
 */
public class PermOOM {
//    -XX:MaxPermSize=10M
    public static void main(String[] args) {
        List<Person> list = Lists.newArrayList();
        while (true){
            Person p = new Person();
            list.add(p);
        }
    }
}
class  Person{
     String name ;
     Integer age ;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public Integer getAge() {
         return age;
     }

     public void setAge(Integer age) {
         this.age = age;
     }
 }

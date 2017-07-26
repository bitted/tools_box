/**
 * @title ZookeeperConfigProfileTest
 * @package com.cyou.hithere.center.util.zk
 * @description: 简理财
 * @copyright: Copyright (c) 2017
 * @company:北京简便快乐信息技术有限公司
 * @author litaijun
 * @date 2017/4/7 17:38
 */
package com.cyou.hithere.center.util.zk;

import com.dangdang.config.service.ConfigGroup;
import com.dangdang.config.service.file.FileConfigGroup;
import com.dangdang.config.service.file.FileConfigProfile;
import com.dangdang.config.service.zookeeper.ZookeeperConfigGroup;
import com.dangdang.config.service.zookeeper.ZookeeperConfigProfile;

import java.util.Map;

/**
 * (类描述：使用当当的配置中心)
 *
 * @author litaijun
 * @create 2017/4/7 17:38
 */
public class ZookeeperConfigProfileTest {

    public static void printGroupProperty(String node, String key) {
        ZookeeperConfigProfile configProfile = new ZookeeperConfigProfile("192.168.199.95:2181", "/projectx/modulex", "1.0.0");
        ConfigGroup zkConfigGroup = new ZookeeperConfigGroup(configProfile, node);
        FileConfigProfile fileConfigProfile = new FileConfigProfile("UTF8", "properties");
        ConfigGroup configGroup = new FileConfigGroup(zkConfigGroup, fileConfigProfile, "classpath:public.properties");

        //获取zk中的node节点下的所有子节点
        for (Map.Entry<String, String> entry : configGroup.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        //通过zkconfig获取本地节点数据，并且相同的节点数据，本地节点覆盖zk节点
        System.out.println(key + "=" + configGroup.get(key));

    }


    public static void main(String[] args) {
        printGroupProperty("public", "hello");

    }


}
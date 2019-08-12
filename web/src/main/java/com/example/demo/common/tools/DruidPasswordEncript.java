package com.example.demo.common.tools;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * Druid密码加密解密工具类
 * 直接将publicKey写在配置文件中的加密方法是很鸡肋的
 * 可以通过publickKey进行解密
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/5
 */
public class DruidPasswordEncript {
    public static void main(String[] args) {
        try {
            String password = "123456";
            String[] arr = ConfigTools.genKeyPair(512);
            // 私钥加密
            System.out.println("privateKey：" + arr[0]);
            // 公钥解密
            System.out.println("publicKey:" + arr[1]);
            System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
        } catch (Exception e) {

        }
    }

}

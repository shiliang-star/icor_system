package com.shiliang.icor.test.singleton;

import org.springframework.boot.web.servlet.server.Session;

/**
 * @Author sl
 * @Date 2021/3/20 20:54
 * @Description TODO
 */
//懒汉式单例
public class Singleton2 {
    //指向自己实例的私有静态引用
    private static Singleton2 singleton2;

    //私有的构造方法
    private Singleton2() {};

    //以自己实例为返回值的静态的共有方法，静态工厂方法
    public static Singleton2 getInstance() {
        //被动创建，在真正需要使用时才去创建
        if (singleton2 == null) {
             singleton2 = new Singleton2();
        }
        return singleton2;
    }
}

package com.shiliang.icor.test.singleton;

/**
 * @Author sl
 * @Date 2021/3/20 20:49
 * @Description TODO
 */
// 线程安全的懒汉式单例
public class Singleton3 {

    //指向自己实例的私有静态引用，主动创建
    private static Singleton3 Singleton3;

    //私有的构造方法
    private Singleton3() {};

    //以自己实例为返回值的静态的公有方法，静态工厂方法
    //使用synchronized修饰，临界资源的同步互斥访问
    public static synchronized Singleton3 getInstance() {
        //被动创建，在真正需要使用时才去创建
        if (Singleton3 == null) {
            Singleton3 = new Singleton3();
        }
        return Singleton3;
    }
}

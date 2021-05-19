package com.shiliang.icor.test.singleton;

/**
 * @Author sl
 * @Date 2021/3/20 20:54
 * @Description TODO
 */
// 线程安全的懒汉式单例
public class Singleton4 {
    //指向自己实例的私有静态引用
    private static Singleton4 singleton4;

    //私有的构造方法
    private Singleton4() {};

    //以自己实例为返回值的静态的共有方法，静态工厂方法
    public static Singleton4 getInstance() {
        // 使用 synchronized 块，临界资源的同步互斥访问
        synchronized (Singleton4.class){
            if (singleton4 == null) {
                singleton4 = new Singleton4();
            }
        }
        return singleton4;
    }
}

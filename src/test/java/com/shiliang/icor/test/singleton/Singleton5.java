package com.shiliang.icor.test.singleton;

/**
 * @Author sl
 * @Date 2021/3/20 20:54
 * @Description TODO
 */
// 线程安全的懒汉式单例
public class Singleton5 {

    //私有的构造方法
    private Singleton5() {};

    // 私有内部类，按需加载，用时加载，也就是延迟加载
    private static class Holder {
        private static Singleton5 singleton5 = new Singleton5();
    }

    //以自己实例为返回值的静态的共有方法，静态工厂方法
    public static Singleton5 getInstance() {
        return Holder.singleton5;
    }
}

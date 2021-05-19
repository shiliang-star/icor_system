package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/24 21:50
 * @Description DCL(Double Check Lock双端检锁机制）
 */
public class SingletonDemo {
    //禁止指令重排，保证可见性
    private static volatile SingletonDemo singletonDemo = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "我是构造器方法SingletonDemo");
    }

    public static SingletonDemo getInstance() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }
    public static void main(String[] args) {
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        for (int i = 0; i < 10; i++) {
            new Thread(SingletonDemo::getInstance,String.valueOf(i)).start();
        }
    }
}

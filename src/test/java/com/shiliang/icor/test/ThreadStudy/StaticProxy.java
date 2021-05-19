package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/1 14:32
 * @Description 静态代理demo(婚庆公司对新郎官的代理）
 */
public class StaticProxy {

    public static void main(String[] args) {
//        Wedding wedding = new Wedding(new Customer());
//        wedding.happyMarry();
        Customer customer = new Customer();
        new Wedding(()-> System.out.println("我爱你")).happyMarry();

    }


}


interface Marry {
    void happyMarry();
}

class Customer implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("客户结婚了");
    }
}


class Wedding implements Marry {

    Marry target;

    public Wedding(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry();
        after();
    }

    public void before() {
        System.out.println("布置婚礼现场，结婚前操作");
    }

    public void after() {
        System.out.println("收尾款，结婚后布置操作");
    }
}

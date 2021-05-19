package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/4 22:56
 * @Description 生产者消费者模式
 */
public class TestPC {

    public static void main(String[] args) {
        Store store = new Store();
        new Provider(store).start();
        new Consumer(store).start();
    }

}

//生产者
class Provider extends Thread {

    Store store;

    public Provider(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i <100; i++) {
            store.makeBread(new Bread(i));
            System.out.println("生产了" + i + "个面包");
        }
    }
}


//消费组
class Consumer extends Thread {

    Store store;

    public Consumer(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Bread bread = store.buyBread();
            System.out.println("消费了第" + bread.id+ "个面包");
        }
    }
}

//面包
class Bread{
    int id;

    public Bread(int id) {
        this.id = id;
    }

}

//商店
class Store {
    //容器大小
    private Bread[] breads = new Bread[10];
    //容器计数值
    private int count = 0;

    //买包子，消费
    public synchronized Bread buyBread() {
        //没有包子了，消费者等待
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费者消费
        count--;
        Bread bread = breads[count];
        //消费了，通知生产者生产
        this.notifyAll();
        return bread;

    }

    //生产包子，生产
    public synchronized void makeBread(Bread bread) {
        //如果容器满了，就需要等待消费
        if (count == breads.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        breads[count] = bread;
        count++;
        //可以通知消费组消费了
        this.notifyAll();

    }


}




package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author sl
 * @Date 2021/3/24 9:31
 * @Description TODO
 */
public class VolatileDemo {


    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    myData.addNum();
                    myData.addNumAtomic();
                }
                }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(myData.num);
        System.out.println(myData.atomicInteger.get());
    }


}

class MyData{

    volatile int num = 0;

    public void addNumTO1024() {
        num=1024;
    }

    public void addNum() {
        num++;
    }

    //保证了原子性，
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addNumAtomic() {
        atomicInteger.getAndIncrement();
    }
}



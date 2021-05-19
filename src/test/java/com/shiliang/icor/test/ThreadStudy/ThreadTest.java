package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author sl
 * @Date 2021/3/9 22:12
 * @Description TODO
 */
public class ThreadTest {
    public static void main(String[] args) {
        TestContainer container = new TestContainer();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                container.add();
                System.out.println(Thread.currentThread().getName()+"变量的值为：" + container.num);
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                container.subtract();
                System.out.println(Thread.currentThread().getName()+"变量的值为：" + container.num);
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                container.add();
                System.out.println(Thread.currentThread().getName()+"变量的值为：" + container.num);
            }
        },"c").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                container.subtract();
                System.out.println(Thread.currentThread().getName()+"变量的值为：" + container.num);
            }
        },"D ").start();
    }
}


class TestContainer {//资源类

    int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public  void add() {
        lock.lock();
        try {
            while (num != 0) {
                try {
                    condition.await();
//                this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public  void subtract() {
        lock.lock();
        try{
            while (num ==0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
           condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

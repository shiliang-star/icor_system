package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author sl
 * @Date 2021/3/9 23:17
 * @Description 精准通知 精准唤醒
 */
public class ThreadTest2 {
    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            try {
                shareResource.print5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                shareResource.print10();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                shareResource.print15();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }
}

class ShareResource{
    private int num = 1;
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num != 1) {
               condition1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 2;
            //通知
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num != 2) {
                condition2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 3;
            //通知
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num != 3) {
                condition3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 1;
            //通知
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}

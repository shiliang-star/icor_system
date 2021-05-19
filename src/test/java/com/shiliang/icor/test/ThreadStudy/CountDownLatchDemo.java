package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.CountDownLatch;

/**
 * @Author sl
 * @Date 2021/3/17 15:04
 * @Description TODO
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            final int tmpt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "  第" + tmpt + "个同学离开了教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "  班长离开了教师");
    }
}

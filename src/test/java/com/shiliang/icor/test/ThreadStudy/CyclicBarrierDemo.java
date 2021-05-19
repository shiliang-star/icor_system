package com.shiliang.icor.test.ThreadStudy;

import net.sf.jsqlparser.expression.StringValue;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @Author sl
 * @Date 2021/3/17 21:17
 * @Description TODO
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int n = 7;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n,()->{
            System.out.println("集其" + n + "龙珠，召唤神龙成功");
        });
        for (int i = 1; i <= n; i++) {
            int temp = i;
            new Thread(()->{
                System.out.println("第" + temp + "颗龙珠集齐了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}

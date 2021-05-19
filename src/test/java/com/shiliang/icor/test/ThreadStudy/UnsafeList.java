package com.shiliang.icor.test.ThreadStudy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sl
 * @Date 2021/3/2 23:04
 * @Description 不安全集合案例
 */
public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(list.size());
    }
}

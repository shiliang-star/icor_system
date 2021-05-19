package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author sl
 * @Date 2021/3/3 21:42
 * @Description 测试JUC
 */
public class TestJuc {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(list.size());
    }
}

package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author sl
 * @Date 2021/3/5 23:30
 * @Description 线程池学习
 */
public class TestPool {
    public static void main(String[] args) {

        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //执行
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        //关闭线程池
        service.shutdown();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName());
    }
}

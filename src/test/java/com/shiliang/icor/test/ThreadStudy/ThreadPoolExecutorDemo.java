package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.*;

/**
 * @Author sl
 * @Date 2021/3/19 9:46
 * @Description TODO
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
//        ExecutorService service = Executors.newFixedThreadPool(3); //固定线程池大小
//        ExecutorService service = Executors.newSingleThreadExecutor(); //单一线程池
//        ExecutorService service = Executors.newCachedThreadPool(); //可变线程池

        //自定义线程池
        ExecutorService service = new ThreadPoolExecutor(3, 9, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 0; i < 20; i++) {
                service.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } finally {
            service.shutdown();
        }

    }
}

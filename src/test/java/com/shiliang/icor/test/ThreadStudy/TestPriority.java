package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/2 21:45
 * @Description TODO
 */
public class TestPriority implements Runnable {


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        TestPriority testPriority = new TestPriority();
        Thread thread1 = new Thread(testPriority);
        thread1.setPriority(1);
        thread1.start();

        Thread thread2 = new Thread(testPriority);
        thread2.setPriority(4);
        thread2.start();

        Thread thread3 = new Thread(testPriority);
        thread3.setPriority(8);
        thread3.start();

        Thread thread4 = new Thread(testPriority);
        thread4.setPriority(Thread.MAX_PRIORITY);
        thread4.start();

    }
}

package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/1 21:44
 * @Description 礼让线程，让当前正在执行的线程暂停，但不阻塞
 *               将线程从运行状态转为执行状态
 *               让cpu重新调度，礼让不一定成功，看cpu心情
 */
public class TestYield implements Runnable {

    public static void main(String[] args) {
        TestYield testYield = new TestYield();
        new Thread(testYield, "a").start();
        new Thread(testYield, "b").start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始运行了");
        Thread.yield();//线程礼让
        System.out.println(Thread.currentThread().getName() + "线程结束执行了");
    }
}

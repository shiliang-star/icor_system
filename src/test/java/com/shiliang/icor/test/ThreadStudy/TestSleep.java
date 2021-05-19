package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/1 21:19
 * @Description 模拟网络延迟
 */
public class TestSleep implements Runnable {

    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            //模拟延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第" + ticketNums-- + "张票");

        }
    }

    public static void main(String[] args) {
        TestSleep testSleep = new TestSleep();
        new Thread(testSleep,"包子").start();
        new Thread(testSleep,"丸子").start();
        new Thread(testSleep,"黄牛党").start();
    }
}

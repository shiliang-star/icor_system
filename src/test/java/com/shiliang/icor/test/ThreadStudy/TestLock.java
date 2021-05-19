package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author sl
 * @Date 2021/3/3 22:44
 * @Description TODO
 */
public class TestLock {
    public static void main(String[] args) {
        BuyTickets buyTickets = new BuyTickets();
        new Thread(buyTickets).start();
        new Thread(buyTickets).start();
        new Thread(buyTickets).start();
    }
}


class BuyTickets implements Runnable {

    private int ticket = 10;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();//加锁
                if (ticket > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticket--);
                } else {
                    break;
                }
            } finally {
                lock.unlock();//释放锁
            }
        }
    }
}

package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/2 22:41
 * @Description 不安全买票案例
 */
public class UnsafeBuyTicket {

    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "学生").start();
        new Thread(buyTicket, "打工仔").start();
        new Thread(buyTicket, "黄牛党").start();
    }

}

class BuyTicket implements Runnable {

    private int ticketNum = 10;

    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    public synchronized void buy() {
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName() + "买了第" + ticketNum-- + "张票");
    }
}

package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.TimeUnit;

/**
 * @Author sl
 * @Date 2021/3/11 13:51
 * @Description 锁
 */
public class ThreadTest3 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendMsg();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
//            phone2.sendEmail();
            phone.hello();
        },"A").start();
    }
}




class Phone{

    public static synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信了");
    }


    public static synchronized void sendEmail() {
        System.out.println("发邮件了");
    }


    public void hello() {
        System.out.println("Hello World");
    }

}

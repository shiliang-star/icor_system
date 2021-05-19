package com.shiliang.icor.test.ThreadStudy;

import lombok.SneakyThrows;

/**
 * @Author sl
 * @Date 2021/3/3 22:03
 * @Description 死锁
 */
public class DeadLock {

    public static void main(String[] args) {
        MakeUp test1 = new MakeUp(1, "灰姑娘");
        MakeUp test2 = new MakeUp(2, "白雪公主");
        new Thread(test1).start();
        new Thread(test2).start();
    }
}


class Lipstick {
}

class Mirror{
}


class MakeUp implements Runnable {

    static Lipstick lipstick = new Lipstick();

    static Mirror mirror = new Mirror();

    private int choice;
    private String girlName;

    public MakeUp(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @SneakyThrows
    @Override
    public void run() {
         makeUp();
    }

    public void makeUp() throws InterruptedException {
        if (choice == 1) {
            synchronized (lipstick) {
                System.out.println(this.girlName + "拿到了口红的锁");
                Thread.sleep(2000);
            }
            synchronized (mirror) {
                System.out.println(this.girlName + "拿到了镜子的锁");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girlName + "拿到了镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipstick) {
                System.out.println(this.girlName + "拿到了口红的锁");
            }
        }
    }
}

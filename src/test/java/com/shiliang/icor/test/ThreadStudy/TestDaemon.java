package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/2 22:14
 * @Description 测试守护线程
 */
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        Thread thread = new Thread(god);
        thread.setDaemon(true);  //默认为false，默认为用户线程，设置成true变成守护线程
        thread.start();
        new Thread(you).start();
    }


}

class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑者你！");
        }
    }
}

class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 365000; i++) {
            System.out.println("祝你一生平安！");
        }
    }
}

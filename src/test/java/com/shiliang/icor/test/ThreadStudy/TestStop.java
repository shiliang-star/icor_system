package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/1 21:11
 * @Description TODO
 */
public class TestStop implements Runnable {

    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run -----thread" + i++);
        }
    }


    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("Main线程--" + i);
            if (i == 900) {
                testStop.flag = false;
                System.out.println("线程该停止了");
            }
        }
    }
}

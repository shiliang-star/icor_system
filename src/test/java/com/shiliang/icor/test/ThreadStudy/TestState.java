package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/1 22:13
 * @Description TODO
 */
public class TestState implements Runnable {


    @Override
    public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("///////////");
    }

    public static void main(String[] args) throws InterruptedException {
        TestState testState = new TestState();
        Thread thread = new Thread(testState);

        Thread.State state = thread.getState();
        System.out.println("线程状态：" + state);

        thread.start();
        state = thread.getState();
        System.out.println("线程状态：" + state);

        while (thread.getState() != Thread.State.TERMINATED) {
            Thread.sleep(100);
            state = thread.getState();//更新线程状态
            System.out.println("线程状态：" + state);
        }
    }
}

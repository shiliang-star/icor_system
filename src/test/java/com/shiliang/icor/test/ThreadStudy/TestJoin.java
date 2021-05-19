package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/1 21:51
 * @Description join合并线程，待此线程执行完成后，再执行其他线程，其他线程阻塞 可以理解为插队
 */
public class TestJoin implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程Vip来了->"+i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //启动线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        //主线程
        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                thread.join();
            }
            System.out.println("Main线程->" + i);
        }
    }
}

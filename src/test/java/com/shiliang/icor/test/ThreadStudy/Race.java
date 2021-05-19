package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/1 9:52
 * @Description 模拟龟兔赛跑
 */
public class Race implements Runnable {

    private static String winner ;

    @Override
    public void run() {
        for (int i = 1; i <=100; i++) {
            //模拟兔子休息
            if (Thread.currentThread().getName().equals("兔子")) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameOver(i)) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

    /**
     * 判断游戏是否结束
     */
    public Boolean gameOver(int step) {
        if (winner != null) {
            return true;
        } else {
            if (step >= 100) {
                winner = Thread.currentThread().getName();
                System.out.println("胜利者是" + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "乌龟").start();
        new Thread(race, "兔子").start();
        new Thread(race, "时亮").start();
    }



}

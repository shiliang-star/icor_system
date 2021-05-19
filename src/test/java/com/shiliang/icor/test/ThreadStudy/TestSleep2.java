package com.shiliang.icor.test.ThreadStudy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author sl
 * @Date 2021/3/1 21:26
 * @Description 每个对象都有1把锁，sleep不会释放锁
 */
public class TestSleep2 {


    public static void main(String[] args) throws InterruptedException {
        //模拟获取当前系统时间
        Date date = new Date(System.currentTimeMillis());
        while (true) {
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
            date=new Date(System.currentTimeMillis()); //更新系统时间
        }
//        tenDown();
    }


    //模拟倒计时
    public static void tenDown() throws InterruptedException {
         int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }
}

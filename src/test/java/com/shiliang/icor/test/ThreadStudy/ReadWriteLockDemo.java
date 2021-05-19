package com.shiliang.icor.test.ThreadStudy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author sl
 * @Date 2021/3/17 22:12
 * @Description TODO
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp);
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp);
            },String.valueOf(i)).start();
        }
    }


}

class MyCache {

    Map<String, String> map = new HashMap<>();
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(int key) {
        lock.writeLock().lock();
        try {
            System.out.println(key + "开始写入");
            map.put(key + "", "" + key);
            System.out.println(key + "写入成功");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(int key) {
        lock.readLock().lock();
        try {
            String s = map.get(key + "");
            System.out.println("读取数据" + s);
        } finally {
            lock.readLock().unlock();
        }
    }

}

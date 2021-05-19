package com.shiliang.icor.test.ThreadStudy;

import cn.hutool.core.lang.UUID;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author sl
 * @Date 2021/3/16 9:52
 * @Description 集合类线程不安全
 *    ConcurrentModificationException  并发修改异常
 *    1. Vector 替换ArrayList
 *    2.Collections.synchronizedList
 *    3.
 */
public class NoSafeDemo {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        Set<String> set = new CopyOnWriteArraySet<>();
        Set<String> set = new HashSet<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    public void listNoSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}


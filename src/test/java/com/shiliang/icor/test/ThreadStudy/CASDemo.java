package com.shiliang.icor.test.ThreadStudy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author sl
 * @Date 2021/3/24 22:32
 * @Description CAS 比较并替换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        System.out.println(atomicInteger.compareAndSet(2, 2021)+"\tcurrent data:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2, 2029)+"\tcurrent data:"+atomicInteger.get());
    }
}

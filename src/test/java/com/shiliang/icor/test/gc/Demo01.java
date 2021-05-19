package com.shiliang.icor.test.gc;

/**
 * @Author sl
 * @Date 2021/3/22 11:47
 * @Description TODO
 */
public class Demo01 {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();//返回Java虚拟机的试图使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();//返回Java虚拟机的内存总量
        System.out.println("MAX_MEMORY=" + maxMemory + "字节、" + (maxMemory / (double) 1024 / 1024) + "MB");
        System.out.println("TOTAL_MEMORY=" + totalMemory + "字节、" + (totalMemory / (double) 1024 / 1024) + "MB");
    }
}

package com.shiliang.icor.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author sl
 * @Date 2021/3/4 16:33
 * @Description 线程内部存储的工具类
 */
@Slf4j
public class ThreadLocalUtils {
    private final static ThreadLocal threadLocal = new ThreadLocal();

    public static void set(Object obj) {
        threadLocal.set(obj);
    }

    public static <T> T get(Class<T> tClass) {
        log.info("强转类型为{}", tClass);
        return (T) threadLocal.get();
    }

    public static <T> T get() {
        return (T) threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}

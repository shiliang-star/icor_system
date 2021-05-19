package com.shiliang.icor.utils;

import java.lang.annotation.*;

/**
 * @Author sl
 * @Date 2021/3/5 14:50
 * @Description 自定义操作日志注解
 */
@Target(ElementType.METHOD)  //注解放置的目标位置，Method是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME)  //注解在哪个阶段执行
@Documented
public @interface OperLog {
    String operModule() default "";//操作模块

    String operType() default ""; //操作类型

    String operDesc() default ""; //操作说明
}

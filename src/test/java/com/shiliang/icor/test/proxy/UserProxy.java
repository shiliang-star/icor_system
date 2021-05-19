package com.shiliang.icor.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author sl
 * @Date 2021/3/28 10:46
 * @Description TODO
 */
public class UserProxy {
    Object target;
    public UserProxy(Object object) {
        this.target = object;
    }

    public Object getProxyInstance() {
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是代理前的业务操作");
                Object invoke = method.invoke(target, args);
                System.out.println("我是代理后的业务操作");
                return invoke;
            }
        });
    }

}

package com.shiliang.icor.test.proxy;

/**
 * @Author sl
 * @Date 2021/3/28 10:55
 * @Description TODO
 */
public class TestProxy {
    public static void main(String[] args) {
//        IUser user = new UserImpl();
//        System.out.println(user.getClass());
//        IUser proxy = (IUser) new UserProxy(user).getProxyInstance();
//        System.out.println(proxy.getClass());
//        proxy.play();


        PersonDao personDao = new PersonDao();
        System.out.println(personDao.getClass());
        PersonDao proxy = (PersonDao) new ProxyFactory(personDao).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.update();
    }
}

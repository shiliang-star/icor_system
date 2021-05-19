package com.shiliang.icor.test.leetcode;

import com.shiliang.icor.test.ThreadStudy.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Author sl
 * @Date 2021/3/20 13:36
 * @Description TODO
 */
public class Demo06 {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> users = Arrays.asList(u1, u2, u3, u4, u5);
        //选出偶数ID且年龄大于24且用户名转为大写且用户字母到排序
//        List<User> collect = users.stream().filter(u -> u.getId() % 2 == 0 && u.getAge() > 24).map(u ->u.setUsername(u.getUsername().toUpperCase())).collect(Collectors.toList());
//        System.out.println(collect);
        users.stream().filter(u -> u.getId() % 2 == 0 && u.getAge() > 24).map(u -> u.getUsername().toUpperCase()).sorted(Comparator.reverseOrder()).limit(1).forEach(System.out::println);
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
//        Consumer<String> consumer = s->{
//            System.out.println(s);
//        };
//        consumer.accept("shiliang");
//
////        Supplier<String> supplier = new Supplier<String>() {
////            @Override
////            public String get() {
////                return "是两大帅哥";
////            }
////        };
//        Supplier<String> supplier = () -> {
//            return "时亮大帅哥";
//        };
//        System.out.println(supplier.get());
//
////        Function<String,Integer> function=new Function<String, Integer>() {
////            @Override
////            public Integer apply(String s) {
////                return 1024;
////            }
////        };
//        Function<String, Integer> function = s -> {
//            return 1024;
//        };
//        System.out.println(function.apply("1234"));
//
////        Predicate<String> predicate=new Predicate<String>() {
////            @Override
////            public boolean test(String s) {
////                return false;
////            }
////        };
//        Predicate<String> predicate=s->{
//            return true;
//        };
//        System.out.println(predicate.test("时亮帅？"));

    }
}

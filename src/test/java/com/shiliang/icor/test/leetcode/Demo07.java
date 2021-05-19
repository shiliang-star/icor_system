package com.shiliang.icor.test.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo07 {
    private String myValue="000000";

    public Demo07(String s){
        this.myValue = s;
    }

    public void printValue(){
        System.out.println(this.myValue);
    }

    static public void changeValue(String s){
        s = "222222";
    }

    public void sortNum(int x,int y,int z) {
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        list.add(z);
        Collections.sort(list);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }


    public static void main(String[] args) {
        String s = "111111";
        Demo07.changeValue(s);

        new Demo07(s).printValue();
        new Demo07(s).sortNum(45,6,767);
    }
}

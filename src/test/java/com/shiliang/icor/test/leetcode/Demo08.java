package com.shiliang.icor.test.leetcode;

import java.io.File;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName Demo08.java
 * @Description TODO
 * @createTime 2021年04月20日 17:41:00
 */
public class Demo08 {

    public static void main(String[] args) {
//        System.out.println(new Demo08().caculate(1));
//        ((Demo08)null).printSum();
        char c='时';
        System.out.println(c);
        String str1="word";
        String str2="555";
        String str3="word555";
        String str4=str1+"555";
        String str5="word"+"555";
        String str6=str1+90;
        System.out.println(str3==str4);
        System.out.println(str3==str5);
        System.out.println(str6);
        System.out.println(File.separator);
    }



    public int caculate(int i){
        int result=0;
        switch (i++){
            case 1:
                result =result+i;
            case 2:
                result=result+i*2;
            case 3:
                result=result+i*3;
        }
        return result;
    }
    public void printSum(){
        System.out.println("Test");
    }
}

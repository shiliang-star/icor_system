package com.shiliang.icor.test.leetcode;

import java.util.HashMap;

/**
 * @Author sl
 * @Date 2021/3/17 17:14
 * @Description 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 *
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 *
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 */
public class Demo03 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        Solution2 solution = new Solution2();
//        boolean b = solution.CheckPermutation("abb", "aab");
        boolean b = solution.CheckPermutation("abc", "bad");
        System.out.println(b);
    }
   static class Solution {
        public boolean CheckPermutation(String s1, String s2) {
            int length1 = s1.length();
            int length2 = s2.length();
            if (length1 != length2) {
                return false;
            }
            HashMap<Character, Integer> hashMap1 = new HashMap();
            char[] chars = s1.toCharArray();
            for (int i = 0; i < length1; i++) {
                hashMap1.put(chars[i], hashMap1.getOrDefault(chars[i], 0) + 1);
            }
            HashMap<Character, Integer> hashMap2 = new HashMap();
            char[] chars1 = s2.toCharArray();
            for (int i = 0; i < length2; i++) {
                hashMap2.put(chars1[i], hashMap2.getOrDefault(chars1[i], 0) + 1);
            }
            for (int i = 0; i < length2; i++) {
                if (hashMap1.get(chars[i]) != hashMap2.get(chars[i])) {
                    return false;
                }
            }
            return true;
        }
    }

   static class Solution2 {
        public boolean CheckPermutation(String s1, String s2) {
            if(s1.length() != s2.length()){
                return false;
            }
            HashMap<Character,Integer> map1 = new HashMap<>();
            HashMap<Character,Integer> map2 = new HashMap<>();
            char[] arr = s1.toCharArray();
            for(int i = 0;i < arr.length;i++){
                map1.put(arr[i],map1.getOrDefault(arr[i],0)+1);
            }
            char[] arr1 = s2.toCharArray();
            for(int i = 0;i < arr1.length;i++){
                map2.put(arr1[i],map2.getOrDefault(arr1[i],0)+1);
            }
            for(int i = 0;i < arr.length;i++){
                if(map1.get(arr[i]) != map2.get(arr[i])){
                    return false;
                }
            }
            return true;
        }
    }

}

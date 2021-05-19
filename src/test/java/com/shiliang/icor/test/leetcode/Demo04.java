package com.shiliang.icor.test.leetcode;

/**
 * @Author sl
 * @Date 2021/3/17 19:14
 * @Description URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
示例 1：

输入："Mr John Smith    ", 13
输出："Mr%20John%20Smith"
示例 2：

输入："               ", 5
输出："%20%20%20%20%20"
 */
public class Demo04 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.replaceSpaces("Mr John Smith    ", 13));
    }

   static class Solution {
       public String replaceSpaces(String S, int length) {
           StringBuilder sb = new StringBuilder();
           for (int i = 0; i < length; i++) {
               char c = S.charAt(i);
               if (' '== c) {
                   sb.append("%20");
                   continue;
               }
               sb.append(c);
           }
           return sb.toString();
       }
   }
}

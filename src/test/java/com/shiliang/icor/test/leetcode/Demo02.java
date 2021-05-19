package com.shiliang.icor.test.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author sl
 * @Date 2021/3/17 16:32
 * @Description 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: true
 */
public class Demo02 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean leetcode = solution.isUnique("kzwunahkiz");
        System.out.println(leetcode);
    }

    /**
     * 先放在map里,并保存次数,然后再根据第二次遍历,如果值是大于一的话就返回false,等遍历完成后,说明都只出现过一次,返回true.
     */
    static class Solution {
        public boolean isUnique(String astr) {
            Map<Character, Integer> map = new HashMap<>();
            char[] chars = astr.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                map.put(chars[i], map.getOrDefault(chars[i], 0)+1);
            }
            for (int i = 0; i < chars.length; i++) {
                if (map.get(chars[i]) > 1) {
                    return false;
                }
            }
            return true;
        }
    }

    static class Solution2 {
        public boolean isUnique(String astr) {
            char[] chars = astr.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] == chars[j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

}





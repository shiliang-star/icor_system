package com.shiliang.icor.test.leetcode;

import java.util.Arrays;

/**
 * @Author sl
 * @Date 2021/3/18 14:42
 * @Description 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 */
public class Demo05 {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        int[] ints = BubbleSort(new int[]{108, 52, 5, 657, 35, 78, 3, 6});
        int[] ints =selectionSort (new int[]{108, 52, 5, 657, 35, 78, 3, 6});
        System.out.println(Arrays.toString(ints));
    }

    static class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            int[] sortArr = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                for (int j = i; j < arr.length; j++) {
                    if (arr[i] > arr[j]) {

                    }
                }
            }
            return null;
        }
    }

    /**
     * 冒泡排序
     * 冒泡排序（Bubble Sort） 最为简单的一种排序，通过重复走完数组的所有元素，通过打擂台的方式两个两个比较，直到没有数可以交换的时候结束这个数，再到下个数，
     * 直到整个数组排好顺序。因一个个浮出所以叫冒泡排序。双重循环时间 O(n^2)
     * <p>
     * <p>
     * <p>
     * 算法描述：
     * 比较相邻两个数据如果。第一个比第二个大，就交换两个数
     * 对每一个相邻的数做同样1的工作，这样从开始一队到结尾一队在最后的数就是最大的数。
     * 针对所有元素上面的操作，除了最后一个。
     * 重复1~3步骤，知道顺序完成。
     *
     * 平均时间复杂度：O(n2)
     * 最好情况：O(n)
     * 最坏情况:O(n2)
     */
    public static int[] BubbleSort(int[] sorts) {
        for (int i = 0; i < sorts.length-1; i++) {  //外层循环控制排序次数
            for (int j = 0; j <sorts.length-1-i; j++) {  //内层循环控制每一趟遍历多少次
                if (sorts[j] > sorts[j+1]) {
                    int temp = sorts[j];
                    sorts[j]=sorts[j+1];
                    sorts[j+1] = temp;
                }
            }
        }
        return sorts;
    }


    /**
     * 选择排序（Selection Sort）
     * 选择排序是表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
     * <p>
     在一个长度为 N 的无序数组中，第一次遍历 n-1 个数找到最小的和第一个数交换。
     第二次从下一个数开始遍历 n-2 个数，找到最小的数和第二个数交换。
     重复以上操作直到第 n-1 次遍历最小的数和第 n-1 个数交换，排序完成。
     平均时间复杂度：O(n2)
     最好情况：O(n2)
     最坏情况:O(n2)
     */

    public static int[] selectionSort(int[] sorts) {
        for (int i = 0; i < sorts.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < sorts.length; j++) {
                if (sorts[minIndex] > sorts[j]) {
                    minIndex = j;
                }
            }
            int temp = sorts[i];
            sorts[i] = sorts[minIndex];
            sorts[minIndex] = temp;
        }
        return sorts;
    }
}























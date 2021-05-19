package com.shiliang.icor.test.leetcode;

import java.util.Arrays;

/**
 * @Author sl
 * @Date 2021/3/16 13:25
 * @Description 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
public class Demo01 {
    public static void main(String[] args) {
        SolutionBest solution = new SolutionBest();
        int n = 5;
        int[][] ints = solution.generateMatrix(n);
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(ints[i]));
        }

    }
}



class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int iterNum = n * n;
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            for (int j=i*n+1; j <= iterNum; j++) {
                if (tmp == n) {
                    tmp = 0;
                    break;
                }
                result[i][tmp] = j;
                tmp++;
            }
        }


        return result;
    }
}

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
class SolutionBest {
    public int[][] generateMatrix(int n) {
        int maxNum = n * n;
        int curNum = 1;
        int[][] matrix = new int[n][n];
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 右下左上
        int directionIndex = 0;
        while (curNum <= maxNum) {
            matrix[row][column] = curNum;
            curNum++;
            int nextRow = row + directions[directionIndex][0];
            int nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || matrix[nextRow][nextColumn] != 0) {
                directionIndex = (directionIndex + 1) % 4; // 顺时针旋转至下一个方向
            }
            row = row + directions[directionIndex][0];
            column = column + directions[directionIndex][1];
        }
        return matrix;
    }
}

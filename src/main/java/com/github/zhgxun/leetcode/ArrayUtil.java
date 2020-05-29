package com.github.zhgxun.leetcode;

/**
 * 数组输出
 */
public class ArrayUtil {

    public static void print(int[][] grid) {
        for (int[] g : grid) {
            for (int row = 0; row < grid[0].length; row++) {
                System.out.print(g[row]);
            }
            System.out.println("");
        }
    }
}

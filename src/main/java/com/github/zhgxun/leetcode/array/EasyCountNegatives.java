package com.github.zhgxun.leetcode.array;

// https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/
public class EasyCountNegatives {

    public static void main(String[] args) {
        EasyCountNegatives object = new EasyCountNegatives();
        System.out.println(object.countNegatives(new int[][]{{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}}));
    }

    public int countNegatives(int[][] grid) {
        int num = 0;
        for (int col = 0; col < grid.length; col++) {
            for (int row = grid[0].length - 1; row >= 0; row--) {
                if (grid[col][row] >= 0) { // 从此开始为正数
                    num += (grid[0].length - row);
                }
            }
        }
        return num;
    }
}

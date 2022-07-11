package com.github.zhgxun.leetcode.array;

// https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
public class MediumMovingCount {

    public static void main(String[] args) {
        MediumMovingCount object = new MediumMovingCount();
        System.out.println(object.movingCount(3, 1, 0));
    }

    private int count;

    public int movingCount(int m, int n, int k) {
        int[][] grid = new int[m][n];
        for (int col = 0; col < m; col++) {
            for (int row = 0; row < n; row++) {
                grid[col][row] = 1;
            }
        }

        dfsMovingCount(grid, 0, 0, k);

        return count;
    }

    private void dfsMovingCount(int[][] grid, int row, int col, int k) {
        if (row < 0 || row >= grid[0].length || col < 0 || col >= grid.length || grid[col][row] == 0) {
            return;
        }

        // 满足则计算否则直接退出
        // 将 col row 拆成个位数之和 大于 k 则退出不再处理
        if (cut(col) + cut(row) > k) {
            return;
        }

        count++;
        grid[col][row] = 0;

        dfsMovingCount(grid, row, col - 1, k);
        dfsMovingCount(grid, row + 1, col, k);
        dfsMovingCount(grid, row, col + 1, k);
        dfsMovingCount(grid, row - 1, col, k);
    }

    private int cut(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private void p(int[][] grid) {
        for (int col = 0; col < grid.length; col++) {
            for (int row = 0; row < grid[0].length; row++) {
                System.out.printf(grid[col][row] + " ");
            }
            System.out.println();
        }
    }
}

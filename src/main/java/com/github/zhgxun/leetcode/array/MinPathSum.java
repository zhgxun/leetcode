package com.github.zhgxun.leetcode.array;

/**
 * 64. 最小路径和
 * <p>
 * https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class MinPathSum {

    // 一维动态规划
    public int minPathSum(int[][] grid) {
        int colLen = grid.length;
        int rowLen = grid[0].length;
        int[] dp = new int[rowLen];
        for (int col = colLen - 1; col >= 0; col--) {
            for (int row = rowLen - 1; row >= 0; row--) {
                if (col == colLen - 1 && row < rowLen - 1) { // 底边
                    dp[row] = grid[col][row] + dp[row + 1];
                } else if (row == rowLen - 1 && col < colLen - 1) { // 右边
                    dp[row] = grid[col][row] + dp[row];
                } else if (col == colLen - 1 && row == rowLen - 1) { // 终点
                    dp[row] = grid[col][row];
                } else { // 普通边
                    dp[row] = grid[col][row] + Math.min(dp[row], dp[row + 1]);
                }
            }
        }

        return dp[0];
    }

    // 二维动态规划
    public int minPathSumV3(int[][] grid) {
        int colLen = grid.length;
        int rowLen = grid[0].length;
        int[][] dp = new int[colLen][rowLen];
        for (int col = colLen - 1; col >= 0; col--) {
            for (int row = rowLen - 1; row >= 0; row--) {
                if (col == colLen - 1 && row < rowLen) { // 底边
                    dp[col][row] = grid[col][row] + dp[col][row + 1];
                } else if (row == rowLen - 1 && col < colLen) { // 右边
                    dp[col][row] = grid[col][row] + dp[col + 1][row];
                } else if (col == colLen - 1 && row == rowLen - 1) { // 终点
                    dp[col][row] = grid[col][row];
                } else { // 其它边正常递推公式
                    dp[col][row] = grid[col][row] + Math.min(dp[col][row + 1], dp[col + 1][row]);
                }
            }
        }

        return dp[0][0];
    }

    // dfs 超出时间限制
    public int minPathSumV2(int[][] grid) {
        return dfs(grid, 0, 0);
    }

    public int dfs(int[][] grid, int col, int row) {
        if (col < 0 || col >= grid.length || row < 0 || row >= grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (col == grid.length - 1 && row == grid[0].length - 1) {
            return grid[col][row];
        }

        return grid[col][row] + Math.min(dfs(grid, col, row + 1), dfs(grid, col + 1, row));
    }
}

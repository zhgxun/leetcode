package com.github.zhgxun.leetcode.array;

/**
 * 62. 不同路径
 * <p>
 * https://leetcode-cn.com/problems/unique-paths/
 */
public class UniquePaths {

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(7, 3));
    }

    // m 是 col， n 是 row
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        // 初始化两条终点边
        for (int i = 0; i < m; i++) {
            dp[n - 1][i] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[i][m - 1] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
            }
        }
        return dp[0][0];
    }
}

package com.github.zhgxun.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 91. 解码方法
 */
public class NumDecodings {

    public static void main(String[] args) {
        System.out.println(new NumDecodings().combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return result;

        Arrays.sort(candidates);
        dfs(candidates, 0, new ArrayList<>(), target);
        return result;
    }

    private void dfs(int[] candidates, int index, List<Integer> res, int sum) {
        if (sum == 0) {
            result.add(new ArrayList<>(res));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum - candidates[i] < 0) break;
            res.add(candidates[i]);
            dfs(candidates, i, res, sum - candidates[i]);
            res.remove(res.size() - 1);
        }
    }

    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') return 0;

        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '0' || s.charAt(i - 1) > '2') return 0;
                else {
                    if (i > 1) dp[i] = dp[i - 2];
                    else dp[i] = 1;
                }
            } else if ((s.charAt(i) <= '6' && s.charAt(i) >= '1' && s.charAt(i - 1) == '2') || s.charAt(i - 1) == '1') {
                if (i > 1) dp[i] = dp[i - 1] + dp[i - 2];
                else dp[i] = dp[i - 1] + 1;
            } else dp[i] = dp[i - 1];
        }
        return dp[n - 1];
    }

    private int maxArea = 0;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int colLen = grid.length, rowLen = grid[0].length;
        for (int col = 0; col < colLen; col++)
            for (int row = 0; row < rowLen; row++)
                if (grid[col][row] == 1) maxArea = Math.max(maxArea, dfs(grid, col, row));

        return maxArea;
    }

    private int dfs(int[][] grid, int col, int row) {
        int colLen = grid.length, rowLen = grid[0].length;
        if (col < 0 || col >= colLen || row < 0 || row >= rowLen || grid[col][row] == 0) return 0;

        int area = 1;
        grid[col][row] = 0;

        area += dfs(grid, col - 1, row);
        area += dfs(grid, col, row + 1);
        area += dfs(grid, col + 1, row);
        area += dfs(grid, col, row - 1);

        return area;
    }
}

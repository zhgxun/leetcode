package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * <p>
 * https://leetcode-cn.com/problems/triangle/
 */
public class MinimumTotal {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));//11
        triangle.add(Arrays.asList(3, 4)); // 9, 10
        triangle.add(Arrays.asList(6, 5, 7)); //7,6,10
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(new MinimumTotal().minimumTotal1(triangle));
    }

    public int minimumTotalV2(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        for (int col = triangle.size() - 2; col >= 0; col--) {
            for (int row = 0; row < triangle.get(col).size(); row++) {
                triangle.get(col).set(row, Math.min(triangle.get(col + 1).get(row), triangle.get(col + 1).get(row + 1)) + triangle.get(col).get(row));
            }
        }

        return triangle.get(0).get(0);
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;

        for (int col = triangle.size() - 2; col >= 0; col--) {
            List<Integer> res = triangle.get(col + 1);
            for (int row = 0; row < triangle.get(col).size(); row++) {
                triangle.get(col).set(row, Math.min(res.get(row), res.get(row + 1)) + triangle.get(col).get(row));
            }
        }

        return triangle.get(0).get(0);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int[] dp = new int[triangle.size() + 1];
        for (int col = triangle.size() - 1; col >= 0; col--) {
            for (int row = 0; row < triangle.get(col).size(); row++) {
                dp[row] = Math.min(dp[row], dp[row + 1]) + triangle.get(col).get(row);
            }
        }

        return dp[0];
    }

    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        // 初始化最后一行
        List<Integer> rows = triangle.get(triangle.size() - 1);
        for (int i = 0; i < rows.size(); i++) {
            dp[triangle.size() - 1][i] = rows.get(i);
        }
        for (int col = triangle.size() - 2; col >= 0; col--) {
            rows = triangle.get(col);
            for (int row = 0; row < rows.size(); row++) {
                dp[col][row] = Math.min(dp[col + 1][row], dp[col + 1][row + 1]) + rows.get(row);
            }
        }

        return dp[0][0];
    }
}

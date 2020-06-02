package com.github.zhgxun.leetcode.array;

/**
 * 121. 买卖股票的最佳时机
 * <p>
 * https://leetcode-cn.com/problemset/all/?search=买卖股票的
 */
public class MaxProfit {

    public int maxProfitV2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[][][] dp = new int[prices.length][3][2];
        dp[0][0][0] = 0; // 无股票无交易
        dp[0][0][1] = -prices[0]; // 买股票

        // 初始交易均无任何利润
        // 交易一次的初始值
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0]; // 此时可以交易一次直接买入股票

        // 交易第二次的初始值
        dp[0][2][0] = 0;
        dp[0][2][1] = 0; // 初始没法交易第二次

        // 开始交易
        for (int i = 1; i < prices.length; i++) {
            // 未曾交易
            dp[i][0][0] = dp[i - 1][0][0]; // 无法卖
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] - prices[i]);

            // 第一次交易 上一次无交易本次交易
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][1][0] - prices[i]);

            // 第二次交易
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][1][1] + prices[i]);
        }

        // 最终的交易最大值为卖股票的三种情形下的最大值
        return Math.max(dp[prices.length - 1][0][0], Math.max(dp[prices.length - 1][1][0], dp[prices.length - 1][2][0]));
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[][][] dp = new int[prices.length][k + 1][2];
        dp[0][0][0] = 0; // 无股票无交易
        dp[0][0][1] = -prices[0]; // 买股票

        for (int i = 0; i < k + 1; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[i];
        }

        // 开始交易
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        // 最终的交易最大值为卖股票的三种情形下的最大值
        return dp[prices.length - 1][k][0];
    }
}

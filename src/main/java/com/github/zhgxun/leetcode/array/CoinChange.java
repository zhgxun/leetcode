package com.github.zhgxun.leetcode.array;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * <p>
 * https://leetcode-cn.com/problems/coin-change/
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        System.out.println(c.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(c.coinChangeV2(new int[]{1, 2, 5}, 11));
    }

    private Integer minCount = Integer.MAX_VALUE;

    // 贪心解法
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        charge(coins, amount, coins.length - 1, 0);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    /**
     * @param coins  硬币数额
     * @param amount 剩余应兑换的钱
     * @param index  当前使用的面币值
     * @param count  已兑换次数
     */
    public void charge(int[] coins, int amount, int index, int count) {
        // 零钱兑换完毕
        if (amount == 0) {
            minCount = Math.min(minCount, count);
            return;
        }
        // 没有可兑换的币值
        if (index < 0) {
            return;
        }
        // k 为每次兑换的面值数量
        for (int k = amount / coins[index]; k >= 0 && k + count < minCount; k--) {
            charge(coins, amount - k * coins[index], index - 1, count + k);
        }
    }

    // 动态规划解法
    public int coinChangeV2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        // 枚举钱数
        for (int i = 1; i <= amount; i++) {
            // 枚举硬币面值
            for (int coin : coins) {
                if (coin <= i) { // 面值要小于钱才可以兑换
                    // dp[i] 当前可兑换的最小次数
                    // dp[i - coin] // 钱数 - 币值 剩下的可兑换最小数量
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // 最终的钱超了则无法兑换, 否则即为最小兑换次数
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

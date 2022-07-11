package com.github.zhgxun.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println(c.forceCharge((new int[]{1, 2, 5}), 11));
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

    // 暴力求解, 超出时间限制
    public int forceCharge(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int minNum = Integer.MAX_VALUE;
        for (int coin : coins) {
            int num = forceCharge(coins, amount - coin);
            if (num == -1) {
                continue;
            }
            minNum = Math.min(minNum, 1 + num);
        }
        return minNum == Integer.MAX_VALUE ? -1 : minNum;
    }

    // 使用缓存求解 - 速度有较大提升, 但是依然不够理想
    public int cacheCharge(int[] coins, int amount) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        return helper(coins, amount, cache);
    }

    public int helper(int[] coins, int amount, Map<Integer, Integer> cache) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        if (cache.containsKey(amount)) {
            return cache.get(amount);
        }

        int minNum = Integer.MAX_VALUE;
        for (int coin : coins) {
            int num = helper(coins, amount - coin, cache);
            if (num == -1) {
                continue;
            }
            minNum = Math.min(minNum, 1 + num);
        }
        cache.put(amount, minNum == Integer.MAX_VALUE ? -1 : minNum);

        return cache.get(amount);
    }

    // 使用动态规划
    public int dp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = amount + 1;
        }

        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

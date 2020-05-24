package com.github.zhgxun.leetcode.array;

import java.util.Arrays;

/**
 * 518. 零钱兑换 II
 * <p>
 * https://leetcode-cn.com/problems/coin-change-2/
 */
public class Change {

    public static void main(String[] args) {
        Change c = new Change();
        System.out.println(c.change(5, new int[]{1, 2, 5}));
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x < amount + 1; ++x) {
                // 当前硬币的组合金额累加上一个硬币的组合金额
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];
    }
}

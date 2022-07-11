package com.github.zhgxun.leetcode.array;

/**
 * 322. 零钱兑换
 *
 * <pre>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 *
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 *
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <pre/>
 */
public class MediumCoinChange {

    public static void main(String[] args) {
        MediumCoinChange object = new MediumCoinChange();
        System.out.println(object.coinChange(new int[]{1, 2, 5}, 11));
    }

    /**
     * 这个题的暴力解法类似贪心法, 先从大面额的硬币开始, 逐渐递归, 但是要注意局部最优未必是最终结果的最优, 需要有撤销操作
     * 因此动态规划自底向上是相对比较好理解的, 即每一种金额的最优就是结果的最优
     *
     * @param coins  目标硬币金额
     * @param amount 目标金额
     * @return 兑换到目标金额的最小硬币数
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i < amount + 1; i++) dp[i] = amount + 1;

        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}

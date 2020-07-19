package com.github.zhgxun.leetcode.array;

import java.util.Arrays;

public class NumArray {

    private int num = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        Arrays.sort(coins);
        dfs(coins, 0, coins.length - 1, amount);
        return num == Integer.MAX_VALUE ? -1 : num;
    }

    private void dfs(int[] coins, int count, int index, int amount) {
        if (index < 0) return;

        if (amount == 0) {
            num = Math.min(num, count);
            return;
        }

        for (int k = amount / coins[index]; k >= 0 && k + count < num; k--) {
            dfs(coins, count + k, index--, amount - k * coins[index]);
        }
    }

    public static void main(String[] args) {
        NumArray object = new NumArray(new int[]{1, 3, 5});
        System.out.println(object.sumRange(0, 2));
        object.update(1, 2);
        System.out.println(object.sumRange(0, 2));
    }

    private final int[] nums;
    private int[] sum;

    public NumArray(int[] nums) {
        this.nums = nums;
        if (nums == null || nums.length == 0) return;
        sum = new int[nums.length];
        toSum(0, nums.length - 1);
    }

    private void toSum(int left, int right) {
        // 可能无需初始化首元素
        if (left == 0) sum[0] = nums[0];
        else left = left - 1;

        for (int i = left + 1; i <= right; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public void update(int i, int val) {
        nums[i] = val;
        toSum(i, nums.length - 1);
    }

    public int sumRange(int i, int j) {
        return sum[j] - (i >= 1 ? sum[i - 1] : 0);
    }
}

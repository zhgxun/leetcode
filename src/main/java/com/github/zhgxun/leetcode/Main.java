package com.github.zhgxun.leetcode;

public class Main {

    public static void main(String[] args) {
        Main object = new Main();
    }

    private int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums, 0, S);
        return count;
    }

    private void dfs(int[] nums, int index, int sum) {
        if (index == nums.length) {
            if (sum == 0) count++;
            return;
        }
        dfs(nums, index + 1, sum - nums[index]);
        dfs(nums, index + 1, sum + nums[index]);
    }
}
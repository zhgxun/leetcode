package com.github.zhgxun.leetcode.array;

public class MaxProduct {

    public static void main(String[] args) {
        System.out.println(new MaxProduct().maxProduct(new int[]{2, 3, -2, 4}));
    }

    public int maxProduct(int[] nums) {
        int[] maxDp = new int[nums.length];
        int[] minDp = new int[nums.length];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        // 原则上就是 当前元素 当前元素乘上一次的最大元素 当前元素乘上一次的最小元素 三个值中取最大值和最小值
        for (int i = 1; i < nums.length; i++) {
            // 维护最大值规划
            maxDp[i] = Math.max(nums[i], Math.max(nums[i] * maxDp[i - 1], nums[i] * minDp[i - 1]));

            // 维护最小值规划
            minDp[i] = Math.min(nums[i], Math.min(nums[i] * maxDp[i - 1], nums[i] * minDp[i - 1]));
        }

        int max = Integer.MIN_VALUE;
        for (int value : maxDp) {
            if (max < value) {
                max = value;
            }
        }

        return max;
    }
}

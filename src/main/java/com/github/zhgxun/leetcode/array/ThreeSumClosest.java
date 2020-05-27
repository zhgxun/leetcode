package com.github.zhgxun.leetcode.array;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * <p>
 * https://leetcode-cn.com/problems/3sum-closest/
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        System.out.println(new ThreeSumClosest().threeSumClosest(new int[]{0, 0, 0}, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        int ans = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                if (sum == target) {
                    return ans;
                } else if (sum - target > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }
}

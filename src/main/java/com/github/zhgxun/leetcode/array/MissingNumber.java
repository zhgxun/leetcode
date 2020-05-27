package com.github.zhgxun.leetcode.array;

public class MissingNumber {

    public static void main(String[] args) {
        System.out.println(new MissingNumber().missingNumber(new int[]{3, 1, 0}));
    }

    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i] && nums[i] < nums.length) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }

        return -1;
    }
}

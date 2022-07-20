package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
public class EasyFindDisappearedNumbers {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        EasyFindDisappearedNumbers object = new EasyFindDisappearedNumbers();
        System.out.println(object.findDisappearedNumbers(nums));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 既然数字在 1-n 之间，则对存在的数字加 n, 不存在的数字不会被累加
        for (int i = 0; i < nums.length; i++) {
            int index = (nums[i] - 1) % nums.length;
            nums[index] += nums.length;
        }

        // 统计缺失的数字
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length) {
                result.add(i + 1);
            }
        }
        return result;
    }

    // 排序的方式, 复杂度满足要求, 但是不是最简单的
    // [4,3,2,7,8,2,3,1] -> [5,6]
    public List<Integer> findDisappearedNumbersV2(int[] nums) {
        // 基数排序
        for (int i = 0; i < nums.length; i++) {
            while (i + 1 != nums[i] && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                int index = nums[i] - 1;
                nums[i] = nums[nums[i] - 1];
                nums[index] = temp;
            }
        }

        // 统计缺失的数字
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                result.add(i + 1);
            }
        }
        return result;
    }
}

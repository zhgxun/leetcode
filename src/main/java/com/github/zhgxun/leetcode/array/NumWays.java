package com.github.zhgxun.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumWays {

    public static void main(String[] args) {
        System.out.println(new NumWays().numWays(3, 2));
        System.out.println(new NumWays().numWays(6, 2));
        System.out.println(new NumWays().numWays(6, 4));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>((int) (nums.length / 0.75 + 1));
        for (int i = 0; i < nums.length; i++) {
            int find = target - nums[i];
            if (map.containsKey(find)) {
                return new int[]{map.get(find), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    int numWays(int n, int k) {
        int[] f = new int[n];
        f[0] = k; // 第一根柱子可以选择k中之一粉刷
        f[1] = k * k; // 第二根柱子本身可以选择 k 中之一种, 跟dp[0] 组合 就是 k * k 种

        // f(n) = f(n - 2) * (k - 1) + f(n - 1) * (k - 1)

        for (int i = 2; i < n; i++) {
            // dp[i] 和 dp[i - 1] 同色 则可以是 k - 1 中的任何一种颜色
            // 说明 dp[i - 2] 一定是不同的颜色 总共 dp[i - 2] * (k - 1) 种
            //
            // dp[i] 和 dp[i - 1] 不同色, 则dp[i] 占 (k - 1) 中一种颜色 总共 dp[i - 1] * (k - 1) 种
            f[i] = f[i - 2] * (k - 1) + f[i - 1] * (k - 1);
        }

        System.out.println(Arrays.toString(f));

        return f[n - 1];
    }
}

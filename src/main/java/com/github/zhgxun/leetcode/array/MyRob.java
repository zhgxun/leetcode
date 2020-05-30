package com.github.zhgxun.leetcode.array;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 213. 打家劫舍 II
 * <p>
 * https://leetcode-cn.com/problems/house-robber-ii/
 */
public class MyRob {

    public static void main(String[] args) {
        System.out.println(new MyRob().rob(new int[]{2, 3, 2}));
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return Math.max(myRob(nums, 0, nums.length - 2), myRob(nums, 1, nums.length - 1));
    }

    public int myRob(int[] nums, int start, int end) {
        int[][] dp = new int[end - start + 1][2];
        dp[0][0] = 0;
        dp[0][1] = nums[start];

        for (int i = start + 1; i <= end; i++) {
            int index = i - start;
            dp[index][0] = Math.max(dp[index - 1][0], dp[index - 1][1]);
            dp[index][1] = dp[index - 1][0] + nums[i];
        }

        return Math.max(dp[end - start][0], dp[end - start][1]);
    }

    Map<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        return rob(root, map);
    }

    public int rob(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int total = root.val;
        if (root.left != null) {
            total += rob(root.left.left, map) + rob(root.left.right, map);
        }
        if (root.right != null) {
            total += rob(root.right.left, map) + rob(root.right.right, map);
        }

        int max = Math.max(total, rob(root.left, map) + rob(root.right, map));
        map.put(root, max);
        return max;
    }

    public int[] dpRob(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        // 左右子树能偷取到的最大钱数
        int[] left = dpRob(root.left);
        int[] right = dpRob(root.right);

        int[] result = new int[2];
        // 本次节不偷
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 本次节点偷
        result[1] = left[0] + right[0] + root.val;
        return result;
    }
}

package com.github.zhgxun.leetcode.array;

/**
 * 53. 最大子序和
 * <pre>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：-1
 *
 * 示例 5：
 * 输入：nums = [-100000]
 * 输出：-100000
 *  
 *
 * 提示：
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <pre/>
 */
public class EasyMaxSubArray {

    public static void main(String[] args) {
        EasyMaxSubArray object = new EasyMaxSubArray();
        System.out.println(object.maxSubArrayForDp(new int[]{1}));
        System.out.println(object.maxSubArrayForDp(new int[]{0}));
        System.out.println(object.maxSubArrayForNormal(new int[]{-1}));
        System.out.println(object.maxSubArrayForNormal(new int[]{-100000}));
        System.out.println(object.maxSubArrayForDp(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    /**
     * 普通解法 也是优化的解法
     * <p>
     * 有一个简单直接的思路就是 一次求和但是要和为正数时加后面的数才可能得到更大的值
     * 否则当前值为更大的值
     *
     * @param nums 原始数组
     * @return 最大的子序和
     */
    public int maxSubArrayForNormal(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            // 如果求和大于0则说明已有的部分累加可能得到更大的值
            if (sum > 0) sum += num;
            else sum = num;
            max = Math.max(max, sum);
        }

        return max;
    }

    /**
     * 动态规划版本
     * 每次保留当前最大的值 当前最大的值(其实就是当前的上一个值)跟当前值相加做比较, 每次保留更大的值
     *
     * @param nums 目标数组
     * @return 最大的子序和
     */
    public int maxSubArrayForDp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // 优化的方式其实就是记录上一个最大值即可, 这样可以省略两个变量
}

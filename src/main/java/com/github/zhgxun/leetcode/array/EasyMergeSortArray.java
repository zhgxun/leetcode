package com.github.zhgxun.leetcode.array;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * <p>
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EasyMergeSortArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        EasyMergeSortArray object = new EasyMergeSortArray();
        object.merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    // 需要使用数组的特性, 从后往前比较, 这样才能避开额外的空间占用
    // 将 m,n 长度自减1变为下标使用, 可以避免定义额外的临时变量
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        m -= 1;
        n -= 1;
        while (m >= 0 && n >= 0) {
            nums1[index--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }

        while (m >= 0) nums1[index--] = nums1[m--];

        while (n >= 0) nums1[index--] = nums2[n--];
    }
}

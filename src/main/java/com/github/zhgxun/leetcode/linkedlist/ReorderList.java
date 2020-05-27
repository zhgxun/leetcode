package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;

/**
 * 给定一个单链表 L：  L0 → L1 → … → Ln-1 → Ln ，
 * 将其重新排列后变为： L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 */
public class ReorderList {

    public void reorderList(ListNode head) {

    }

    public static void main(String[] args) {
        System.out.println(new ReorderList().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(new ReorderList().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(new ReorderList().search(new int[]{1}, 0));
        System.out.println(new ReorderList().search(new int[]{1}, 1));
        System.out.println(new ReorderList().search(new int[]{1, 3}, 3));
    }

    public int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // 1. 临界值因为中点可谓相间的位置故需要考虑等于
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }

            // 中点的值跟左值比较
            // 2. 这个等于比较坑爹
            if (nums[0] <= nums[middle]) { // 如果左值比中点的值小说明左半部分是递增区间
                // 3. 这里也需要考虑相等
                if (target >= nums[0] && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else { // 旋转点在右侧
                // 4. 这里也要考虑相等, 好在直接上二分查找, 比较好了
                if (nums[middle] < target && target <= nums[nums.length - 1]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }

        return -1;
    }
}

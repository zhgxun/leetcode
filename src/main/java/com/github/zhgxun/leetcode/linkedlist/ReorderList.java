package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个单链表 L：  L0 → L1 → … → Ln-1 → Ln ，
 * 将其重新排列后变为： L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 */
public class ReorderList {

    public static void main(String[] args) {
        ListNode node = new ReorderList().removeDuplicateNodes(ListNodeUtil.getListNode(new int[]{1, 2, 3, 3, 2, 1}));
        ListNodeUtil.print(node);
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode node = new ListNode(0);
        ListNode nodeHead = node;
        ListNode temp = head;
        while (temp != null) {
            if (!set.contains(temp.val)) {
                set.add(temp.val);
                node.next = temp;
                node = node.next;
            }
            temp = temp.next;
        }
        node.next = null;

        return nodeHead.next;
    }

    public void reorderList(ListNode head) {
        // 1. 找链表中点 快慢指针法 慢指针每次走一步, 快指针每次走两步
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 反转后半段链表
        ListNode tail = slow.next;
        slow.next = null;
        ListNode newHead = null;
        while (tail != null) {
            ListNode nextNode = tail.next;
            tail.next = newHead;
            newHead = tail;
            tail = nextNode;
        }

        // 3. 依次拼接两段链表
        ListNode temp = head;
        while (newHead != null) {
            ListNode nextNode = newHead.next;
            newHead.next = temp.next;
            temp.next = newHead;
            temp = temp.next.next;

            newHead = nextNode;
        }
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

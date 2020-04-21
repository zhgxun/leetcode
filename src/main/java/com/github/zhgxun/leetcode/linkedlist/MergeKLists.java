package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 23. 合并K个排序链表
 * <p>
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class MergeKLists {

    public static void main(String[] args) {
        ListNode[] nodes = new ListNode[]{ListNodeUtil.getListNode(new int[]{1, 4, 5}), ListNodeUtil.getListNode(new int[]{1, 3, 4}), ListNodeUtil.getListNode(new int[]{2, 6})};
        ListNodeUtil.print(mergeKLists(nodes));
    }

    // 两两合并不过执行效率不高
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        for (ListNode node : lists) {
            head = MergeTwoLists.mergeTwoLists(head, node);
        }

        return head;
    }
}

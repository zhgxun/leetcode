package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 21. 合并两个有序链表
 * <p>
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode a = ListNodeUtil.getListNode(new int[]{1, 1, 1, 1, 2, 4});
        ListNode b = ListNodeUtil.getListNode(new int[]{1, 3, 4, 5});
        ListNodeUtil.print(mergeTwoLists(a, b));
    }

    // 合并两个有序链表比较简单
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = new ListNode(0);

        ListNode node = head;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                node.next = l2;

                l2 = l2.next;
            } else if (l2 == null) {
                node.next = l1;

                l1 = l1.next;
            } else if (l1.val > l2.val) {
                node.next = l2;

                l2 = l2.next;
            } else {
                node.next = l1;

                l1 = l1.next;
            }

            node = node.next;
        }

        return head.next;
    }
}

package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 86. 分隔链表
 * <p>
 * https://leetcode-cn.com/problems/partition-list/
 * <p>
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 */
public class Partition {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.getListNode(new int[]{1, 4, 3, 2, 5, 2});
        ListNodeUtil.print(partition(head, 3));
    }

    // 有时候解题不要太死
    // 要灵活运用链表, 链表的好处就是可以随时有头, 可以随时拼接链表
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode before = new ListNode(0);
        ListNode beforeTail = before;
        ListNode after = new ListNode(0);
        ListNode afterTail = after;

        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                beforeTail.next = node;
                beforeTail = beforeTail.next;
            } else {
                afterTail.next = node;
                afterTail = afterTail.next;
            }
            node = node.next;
        }
        afterTail.next = null;

        beforeTail.next = after.next;

        return before.next;
    }
}

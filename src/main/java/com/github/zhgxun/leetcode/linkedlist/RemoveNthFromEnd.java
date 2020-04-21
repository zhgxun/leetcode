package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 19. 删除链表的倒数第N个节点
 * <p>
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * <p>
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
 */
public class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5, 6});
        ListNodeUtil.print(removeNthFromEnd(head, 4));
    }

    // 属于简单级别
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }

        int find = len - n;
        if (find == 0) {
            return head.next;
        }

        // 找到待删除节点的前一个节点, 直接引用至下一个节点即可
        node = head;
        while (find-- > 1) {
            node = node.next;
        }
        // 当前节点的下一个为待删除的节点
        node.next = node.next.next;

        return head;
    }
}

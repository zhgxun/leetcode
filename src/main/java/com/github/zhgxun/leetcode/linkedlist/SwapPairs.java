package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 24. 两两交换链表中的节点
 * <p>
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class SwapPairs {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5});
        ListNodeUtil.print(swapPairs(head));
    }

    // 两两交换节点的值注意移动的步数
    public static ListNode swapPairs(ListNode head) {
        // 没有节点或者只有一个节点
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode(0);
        ListNode temp = pre;
        ListNode node = head, cur, next;
        while (node != null && node.next != null) {
            ListNode nextNode = node.next.next;

            cur = node;
            next = node.next;

            // 注意节点的交换, 画图分析即可
            cur.next = next.next;
            next.next = cur;
            pre.next = next;
            pre = cur;

            node = nextNode;
        }

        return temp.next;
    }
}

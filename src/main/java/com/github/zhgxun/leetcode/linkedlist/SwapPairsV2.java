package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 24. 两两交换链表中的节点
 * <p>
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 */
public class SwapPairsV2 {

    public static void main(String[] args) {
        ListNodeUtil.print(new SwapPairsV2().swapPairs(ListNodeUtil.getListNode(new int[]{1, 2, 3, 4})));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = new ListNode(0);
        ListNode pre = node;
        ListNode cur = head, next;
        while (cur != null && cur.next != null) {
            // 提前记录下一次迭代的节点
            ListNode nextNode = cur.next.next;

            // 需要临时保存下一个节点并注意转换关系
            next = cur.next;
            cur.next = cur.next.next;
            next.next = cur;
            pre.next = next;
            pre = cur;

            cur = nextNode;
        }

        return node.next;
    }
}

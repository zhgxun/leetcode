package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 92. 反转链表 II
 * <p>
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * <p>
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 */
public class ReverseBetween {

    public static void main(String[] args) {
        ListNodeUtil.print(reverseBetween(ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5}), 2, 4));
        ListNodeUtil.print(reverseBetween(ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5}), 1, 4));
        ListNodeUtil.print(reverseBetween(ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5}), 1, 5));
        ListNodeUtil.print(reverseBetween(ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5}), 2, 2));
    }

    // 几个关键节点
    // 1. 待反转的尾节点
    // 2. 反转的头结点
    // 3. 反转的尾节点
    // 4. 反转后的尾节点的头结点
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = new ListNode(0);
        node.next = head;
        ListNode preTail = node;
        ListNode revHead = null;

        int i = 1;

        while (head != null) {
            ListNode nextNode = head.next;

            if (i + 1 == m) {
                // 找到未被反转段的尾节点
                preTail = head;
            } else if (i == m) {
                // 找到待反转段的头结点
                revHead = head;
            } else if (i > m && i <= n) {
                // 该部分需要反转, 头插法反转链表
                head.next = preTail.next;
                preTail.next = head;
            }

            // 反转完毕后保留链表不要继续被改动
            // 此时的节点是反转剩余的头节点
            if (i > n) {
                break;
            }

            i++;
            head = nextNode;
        }

        // 反转前链表的头结点是反转后链表的尾节点, 直接拼接剩余未被反转链表的头结点即可
        revHead.next = head;

        return node.next;
    }
}

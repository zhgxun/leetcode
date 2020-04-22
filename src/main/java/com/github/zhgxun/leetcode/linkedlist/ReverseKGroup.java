package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 25. K 个一组翻转链表
 * <p>
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * <p>
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序
 */
public class ReverseKGroup {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5, 6});
        ListNodeUtil.print(reverseKGroup(head, 4));
    }

    // 按段反转
    // 注意链表的反转可以借助栈结构天然生成
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode(0);
        ListNode tempHead = pre, node = head, cur;

        while (node != null) {

            // k个节点组成的一段链表
            ListNode temp = new ListNode(0);
            ListNode curHead = temp;
            // 元素不够时直接结束即可
            boolean isSkip = false;
            for (int i = 0; i < k; i++) {
                if (node == null) {
                    isSkip = true;
                    break;
                }

                temp.next = node;
                temp = temp.next;

                node = node.next;
            }
            temp.next = null;
            if (!isSkip) {
                // 反转当前段链表
                cur = AddTwoNumbers.rev(curHead.next);

                // 交换
                pre.next = cur;
                pre = curHead.next;
            } else {
                pre.next = curHead.next;
            }
        }

        return tempHead.next;
    }
}

package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 234. 回文链表
 * <p>
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * <p>
 * 请判断一个链表是否为回文链表。
 */
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(ListNodeUtil.getListNode(new int[]{1, 2})));
        System.out.println(isPalindrome(ListNodeUtil.getListNode(new int[]{1, 2, 2, 1})));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 链表的长度
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }

        // 反转部分链表
        ListNode temp = new ListNode(0);
        temp.next = head;
        int half = len / 2;
        ListNode cur = head.next;
        while (--half > 0) {
            ListNode nextNode = cur.next;
            cur.next = temp.next;
            temp.next = cur;

            cur = nextNode;
        }
        head.next = cur;

        // 前半部分链表已被反转
        ListNode right = null;
        // 如果是偶数段则下一个节点即为当前节点
        if (len % 2 == 0) {
            right = cur;
        } else {
            // 奇数段则围绕当前节点对称
            right = cur.next;
        }

        ListNode left = temp.next;
        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }
}

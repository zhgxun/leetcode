package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 445. 两数相加 II
 * <p>
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 * <p>
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 哈哈哈, 这个操作其实就是借助栈数据结构来完成, 不过需要两个栈空间, 其实和反转链表应该差别不大了
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNodeUtil.print(addTwoNumbers(l1, l2));
    }

    // 常规解法
    // 但是反转了链表, 进阶难度是不反转链表
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode sumNode = head;

        ListNode a = rev(l1);
        ListNode b = rev(l2);

        // 记录余数
        int remain = 0;

        while (a != null || b != null) {
            int c = a != null ? a.val : 0;
            int d = b != null ? b.val : 0;
            // 两数之和加余数为总和
            int sum = c + d + remain;
            // 记录该次的余数
            remain = sum / 10;

            sumNode.next = new ListNode(sum % 10);
            sumNode = sumNode.next;

            if (a != null) {
                a = a.next;
            }
            if (b != null) {
                b = b.next;
            }
        }

        // 处理余数
        if (remain > 0) {
            sumNode.next = new ListNode(remain);
        }

        return rev(head.next);
    }

    // 反转链表
    public static ListNode rev(ListNode head) {
        ListNode nodeHead = null;

        ListNode temp = head;
        while (temp != null) {
            ListNode nextNode = temp.next;
            temp.next = nodeHead;
            nodeHead = temp;

            temp = nextNode;
        }

        return nodeHead;
    }
}

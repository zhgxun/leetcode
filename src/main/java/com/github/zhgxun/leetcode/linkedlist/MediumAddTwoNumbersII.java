package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 * <p>
 */
public class MediumAddTwoNumbersII {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        MediumAddTwoNumbersII object = new MediumAddTwoNumbersII();
        ListNodeUtil.print(object.addTwoNumbersForDeque(l1, l2));
    }

    // 反转链表的实现很简单, 只需要反转三次链表接口
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode dummyHead = dummy;
        l1 = reverseNode(l1);
        l2 = reverseNode(l2);

        int remain = 0;
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int val = a + b + remain;
            remain = val / 10;

            dummy.next = new ListNode(val % 10);
            dummy = dummy.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (remain > 0) {
            dummy.next = new ListNode(remain);
        }

        return reverseNode(dummyHead.next);
    }

    public ListNode reverseNode(ListNode head) {
        ListNode dummy = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy;
            dummy = head;

            head = next;
        }

        return dummy;
    }

    // 使用栈的方式无需反转链表, 但是需要链表头插法
    // 头插法相当于声调一次链表遍历的时间
    // 显然用栈的方式更简单, 因为栈的结构是现成的, 但是两种方式的性能是一致的
    public ListNode addTwoNumbersForDeque(ListNode l1, ListNode l2) {
        Deque<Integer> l1Stack = new LinkedList<>();
        while (l1 != null) {
            l1Stack.push(l1.val);
            l1 = l1.next;
        }
        Deque<Integer> l2Stack = new LinkedList<>();
        while (l2 != null) {
            l2Stack.push(l2.val);
            l2 = l2.next;
        }

        ListNode dummy = null;
        int remain = 0;
        while (!l1Stack.isEmpty() || !l2Stack.isEmpty() || remain != 0) {
            int a = l1Stack.isEmpty() ? 0 : l1Stack.pop();
            int b = l2Stack.isEmpty() ? 0 : l2Stack.pop();
            int val = a + b + remain;

            remain = val / 10;
            ListNode node = new ListNode(val % 10);
            node.next = dummy;
            dummy = node;
        }

        return dummy;
    }
}

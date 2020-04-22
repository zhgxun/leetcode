package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 61. 旋转链表
 * <p>
 * https://leetcode-cn.com/problems/rotate-list/
 * <p>
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 */
public class RotateRight {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5});
        ListNodeUtil.print(rotateRight(head, 2));

        ListNodeUtil.print(rotateRight(ListNodeUtil.getListNode(new int[]{0, 1, 2}), 4));
    }

    // 思路
    // 1. 需要知道题目的本质其实不是一个节点一个节点的移动, 虽然描述是这样的
    // 2. 为了找到移动位置比链表长时来回查找的问题, 需要将链表临时变为环形链表
    // 3. 将环形链表拆成单链表
    // 4. 新的头节点和尾节点的确定
    // 5. 新的头结点 n - k%n
    // 6. 新的尾节点 n - k%n - 1
    // 7. 注意移动位置可以超过链表的长度, 故取余的对象是链表长度
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = head;

        int len = 0;
        // 链表成环
        while (node.next != null) {
            len++;
            node = node.next;
        }
        node.next = head;
        len += 1;

        for (int i = 0; i < len - k % len - 1; i++) {
            head = head.next;
        }

        // 此时head为尾节点, 下一个元素为头结点
        ListNode newHead = head.next;
        // 将环形链表处理为单链表
        head.next = null;

        return newHead;
    }
}

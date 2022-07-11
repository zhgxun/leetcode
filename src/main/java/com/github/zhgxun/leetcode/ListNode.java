package com.github.zhgxun.leetcode;

/**
 * 通用单链表结构
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode node) {
        this.val = x;
        this.next = node;
    }
}

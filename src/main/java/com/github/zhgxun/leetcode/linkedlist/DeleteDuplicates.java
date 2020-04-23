package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 82. 删除排序链表中的重复元素 II
 * <p>
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 * <p>
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 */
public class DeleteDuplicates {

    public static void main(String[] args) {
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.getListNode(new int[]{1, 2, 3, 3, 4, 4, 5})));
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.getListNode(new int[]{1, 1, 1, 2, 3})));
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.getListNode(new int[]{1, 1, 1, 1, 2, 2})));
        ListNodeUtil.print(deleteDuplicates(ListNodeUtil.getListNode(new int[]{1, 2, 2})));
    }

    // 简单版本的删除重复节点
    public static ListNode deleteDuplicatesSimple(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = new ListNode(0);
        ListNode pre = node;

        ListNode temp = head;
        while (temp != null) {
            while (temp.next != null && temp.val == temp.next.val) {
                temp = temp.next;
            }
            pre.next = temp;
            pre = pre.next;

            temp = temp.next;
        }

        return node.next;
    }

    // 连重复节点一并删除
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = new ListNode(0);
        ListNode pre = node;

        ListNode temp = head;
        while (temp != null) {
            boolean move = false;
            while (temp.next != null && temp.val == temp.next.val) {
                temp = temp.next;
                move = true;
            }

            // 此时不能处理因为当前节点可能跟后面的节点重复
            // 有移动过节点则需要继续比较
            if (!move) {
                pre.next = temp;
                pre = pre.next;
            } else {
                // 防止最后的原始引用
                pre.next = null;
            }

            temp = temp.next;
        }

        return node.next;
    }
}

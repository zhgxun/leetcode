package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 148. 排序链表
 * 给你链表的头结点 head，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * 你可以 在 O(NlogN) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
public class MediumSortList {

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(7);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next = new ListNode(3);

        MediumSortList object = new MediumSortList();
        ListNodeUtil.print(object.sortList(head));
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 1. 快慢指针找链表的中点, 需要提前进入下一个节点, 这个case只针对4个的时候不通过
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 将链表拆分为两端
        ListNode temp = slow.next;
        slow.next = null;

        // 3. 对两段链表进行排序
        ListNode left = sortList(head);
        ListNode right = sortList(temp);

        // 4. 合并两段有序链表
        ListNode node = new ListNode(0);
        ListNode nodeHead = node;
        while (left != null && right != null) {
            if (left.val >= right.val) {
                node.next = right;
                right = right.next;
            } else {
                node.next = left;
                left = left.next;
            }
            node = node.next;
        }

        if (left != null) node.next = left;
        if (right != null) node.next = right;

        return nodeHead.next;
    }
}

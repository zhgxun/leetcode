package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 206. 反转链表
 * 反转一个单链表
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class EasyReverseList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        EasyReverseList object = new EasyReverseList();
        ListNodeUtil.print(object.reverseList(head));
    }

    public ListNode reverseList(ListNode head) {
        ListNode node = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = node;
            node = head;

            head = nextNode;
        }

        return node;
    }
}

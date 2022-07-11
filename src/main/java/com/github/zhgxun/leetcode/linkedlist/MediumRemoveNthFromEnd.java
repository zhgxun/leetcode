package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 19. 删除链表的倒数第 N 个结点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 */
public class MediumRemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        MediumRemoveNthFromEnd object = new MediumRemoveNthFromEnd();
        ListNode node = object.removeNthFromEnd(head, 2);
        ListNodeUtil.print(node);
    }

    /**
     * 快慢指针法
     * 快指针与慢指针之间相距n个距离, 当快指针走到链表尾部时慢指针刚好是待删除节点的上一个节点
     *
     * @param head 头结点
     * @param n    待删除节点距离末尾的个数
     * @return 删除节点后的链表头结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;

        ListNode node = new ListNode(0, head);
        ListNode slow = node, fast = head;
        while (n-- > 0) fast = fast.next;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return node.next;
    }
}

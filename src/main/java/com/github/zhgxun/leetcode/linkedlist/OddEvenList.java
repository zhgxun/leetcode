package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 328. 奇偶链表
 * <p>
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 * <p>
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推
 */
public class OddEvenList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        ListNodeUtil.print(oddEvenList(node1));

        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(7);
        ListNodeUtil.print(oddEvenList(head));

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        ListNodeUtil.print(oddEvenList(head1));
    }

    // 一开始没注意其实最简单的思路也是符合题目要求的, 即原地的想法
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 奇数节点的尾
        ListNode oddTail = head;
        ListNode evenHead = head.next;
        // 偶数节点的尾
        ListNode evenTail = evenHead;

        int i = 1;
        ListNode node = evenTail.next;
        while (node != null) {
            // 记忆原始链表
            ListNode nextNode = node.next;

            // 处理当前临时节点
            ListNode curNode = node;
            // 将当前节点的下一个节点清空不引用原始链表
            node.next = null;

            if (i % 2 == 0) {
                // 偶数节点
                evenTail.next = curNode;
                evenTail = curNode;
            } else {
                // 奇数节点
                oddTail.next = curNode;
                curNode.next = evenHead;
                oddTail = curNode;
            }

            i++;
            node = nextNode;
        }

        // 首次提交没考虑到这个特殊条件
        // 特殊情况比如链表仅有3个元素时需要将偶数节点的尾节点处理为空节点
        evenTail.next = null;

        return head;
    }

    public static ListNode oddEvenListV2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 记录奇数链表的尾部
        ListNode odd = head;
        // 记录偶数链表的尾部
        ListNode even = head.next;
        // 临时保存偶数链表头指针
        ListNode evenHead = even;
        // 至少存在第三个元素
        while (even != null && even.next != null) {
            // 奇数链表的尾部拼接偶数链表的下一个元素
            odd.next = even.next;
            // 记录奇数链表的尾部
            odd = odd.next;
            // 偶数链表的下一个为奇数链表的下一个元素
            even.next = odd.next;

            // 遍历链表
            even = even.next;
        }

        // 奇数链表的尾部拼接上偶数链表的头部
        odd.next = evenHead;

        return head;
    }
}

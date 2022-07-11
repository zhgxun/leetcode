package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 92. 反转链表 II
 * <p>
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MediumReverseBetween {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        MediumReverseBetween object = new MediumReverseBetween();
        ListNodeUtil.print(object.reverseBetween(head, 2, 4));
    }

    // 使用头插法 需要先找到 preNode 节点, 一次 left 数量的遍历即可
    // 然后一直头插法即可完成
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        // 找到带反转位置的上一个节点
        ListNode preNode = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            preNode = preNode.next;
        }

        // 接下来是待反转的部分
        ListNode curNode = preNode.next;
        ListNode nextNode;
        for (int i = 0; i < right - left; i++) {
            nextNode = curNode.next;
            curNode.next = nextNode.next;
            nextNode.next = preNode.next;
            preNode.next = nextNode;
        }

        return dummyNode.next;
    }
}

package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 面试题52. 两个链表的第一个公共节点
 * <p>
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 * <p>
 * 输入两个链表，找出它们的第一个公共节点。
 */
public class GetIntersectionNode {

    public static void main(String[] args) {
        ListNode aNode = ListNodeUtil.getListNode(new int[]{1});
        ListNode bNode = aNode.next;
        ListNodeUtil.print(getIntersectionNode(aNode, bNode));
    }

    // 比较直接和简单的方式是借助字典, 不过不满足空间复杂度的要求
    // 数学方法也很简单, 不过要多循环几次, 但总的时间复杂度符合要求
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int aLen = 0;
        ListNode aNode = headA;
        while (aNode != null) {
            aLen++;
            aNode = aNode.next;
        }

        int bLen = 0;
        ListNode bNode = headB;
        while (bNode != null) {
            bLen++;
            bNode = bNode.next;
        }

        int step;
        if (aLen > bLen) {
            step = aLen - bLen;
            while (step-- > 0) {
                headA = headA.next;
            }
        } else {
            step = bLen - aLen;
            while (step-- > 0) {
                headB = headB.next;
            }
        }

        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }
}

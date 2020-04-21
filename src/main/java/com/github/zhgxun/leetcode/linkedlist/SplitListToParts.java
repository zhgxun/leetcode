package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 725. 分隔链表
 * <p>
 * https://leetcode-cn.com/problems/split-linked-list-in-parts/
 * <p>
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 * <p>
 * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 * <p>
 * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 * <p>
 * 返回一个符合上述规则的链表的列表。
 * <p>
 * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 * <p>
 * 输入:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * 输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * 解释:
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度
 */
public class SplitListToParts {

    public static void main(String[] args) {
        ListNode head = ListNodeUtil.getListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        ListNode[] nodes = splitListToParts(head, 3);
        for (ListNode node : nodes) {
            ListNodeUtil.print(node);
        }

        ListNode node = ListNodeUtil.getListNode(new int[]{1, 2, 3});
        ListNode[] listNodes = splitListToParts(node, 5);
        for (ListNode n : listNodes) {
            ListNodeUtil.print(n);
        }
    }

    // 这个题的技巧点是怎么判断每块的大小
    // 前面处理的次数少于余数的则多一个
    // 如果链表有 N 个结点，则分隔的链表中每个部分中都有 n/k 个结点，且前 N%k 部分有一个额外的结点
    // 现在对于每个部分，我们已经计算出该部分有多少个节点：width + (i < remainder ? 1 : 0)
    public static ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode node = root;
        while (node != null) {
            len++;
            node = node.next;
        }

        // 被分成的块数平均长度
        int width = len / k;
        // 需要补余的块数
        int remain = len % k;

        ListNode[] result = new ListNode[k];

        for (int i = 0; i < k; i++) {
            ListNode curNode = new ListNode(0);
            ListNode curHead = curNode;

            // 一个连续部分的元素个数, 最少有 width 个
            for (int j = 0; j < width + (i < remain ? 1 : 0); j++) {
                curNode.next = root;
                curNode = curNode.next;

                root = root.next;
            }
            curNode.next = null;

            result[i] = curHead.next;
        }

        return result;
    }
}

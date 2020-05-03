package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 1171. 从链表中删去总和值为零的连续节点
 * <p>
 * https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 * <p>
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 * <p>
 * 删除完毕后，请你返回最终结果链表的头节点。
 */
public class RemoveZeroSumSublist {

    public static void main(String[] args) {
        ListNode node = ListNodeUtil.getListNode(new int[]{1, 2, 3, -3, -2});
        ListNodeUtil.print(removeZeroSumSublist(node));
    }

    // 前缀和好特别呀
    public static ListNode removeZeroSumSublist(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        // 顺序记录每个节点之前对应的前序和
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            map.put(sum, node);
        }

        sum = 0;
        // 顺序检查每个节点的前序和, 如果相等则说明两个节点之间的和为0可以直接替换即可
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            // 说明当前节点之前的前序和和某个节点的前序和相等, 当前节点和某个节点之间的节点和为0
            // 故直接去掉中间节点即可, 每个节点都检查一遍
            node.next = map.get(sum).next;
        }

        return dummy.next;
    }
}

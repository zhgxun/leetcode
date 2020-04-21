package com.github.zhgxun.leetcode;

public class ListNodeUtil {

    /**
     * 简单打印单链表
     *
     * @param node 单链表
     */
    public static void print(ListNode node) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (node != null) {
            sb.append(node.val);
            sb.append("->");

            node = node.next;

            if (i++ >= 20) {
                break;
            }
        }

        sb.append("null");

        System.out.println(sb.toString());
    }

    /**
     * 链表生成
     *
     * @param list 原始数据元素
     * @return 生成的新链表
     */
    public static ListNode getListNode(int[] list) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i : list) {
            node.next = new ListNode(i);
            node = node.next;
        }

        return head.next;
    }
}

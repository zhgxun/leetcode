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
}

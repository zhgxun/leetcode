package com.github.zhgxun.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/
public class MediumCopyRandomList {

    private final Map<Node, Node> cache = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (!cache.containsKey(head)) {
            Node node = new Node(head.val); // 拷贝当前节点
            cache.put(head, node);
            node.next = copyRandomList(head.next);
            node.random = copyRandomList(head.random);
        }

        return cache.get(head);
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

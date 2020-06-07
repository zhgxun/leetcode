package com.github.zhgxun.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU缓存机制
 * <p>
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class LRUCache {

    // 哈希表映射
    private final Map<Integer, LinkedNode> cache = new HashMap<>();
    private int size; // 元素个数
    private final int capacity; // 容量
    // 双向链表
    private final LinkedNode head;
    private final LinkedNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 初始化伪节点并构建双向关系
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    // 获取一个元素
    public int get(int key) {
        LinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果存在则将节点移动至头部
        moveToHead(node);
        return node.value;
    }

    // 向缓存中添加元素
    public void put(int key, int value) {
        LinkedNode node = cache.get(key);
        if (node == null) { // 不存在则添加
            // 添加至头部和缓存中
            LinkedNode newNode = new LinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;

            // 容量已满则移除尾部元素
            if (size > capacity) {
                LinkedNode curTail = removeTail();
                cache.remove(curTail.key);
                size--;
            }
        } else {
            // 否则将元素移动至头部
            node.value = value;
            moveToHead(node);
        }
    }

    // 添加元素至头部
    private void addToHead(LinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // 移除一个节点
    private void removeNode(LinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 将节点移动到头部
    private void moveToHead(LinkedNode node) {
        removeNode(node); // 移除节点
        addToHead(node); // 添加节点至头部
    }

    // 移除尾节点
    private LinkedNode removeTail() {
        LinkedNode res = tail.prev; // 取出尾节点
        removeNode(res); // 直接移除即可
        return res;
    }

    // 双向链表
    public static class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode() {
        }

        public LinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

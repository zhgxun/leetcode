package com.github.zhgxun.leetcode;

/**
 * 循环队列
 * <p>
 * 使用单链表特别简单
 */
public class MyCircularQueue {

    private Node head, tail;
    private int count;
    private final int capacity;

    public MyCircularQueue(int k) {
        this.count = 0;
        this.capacity = k;
    }

    // 向循环队列插入一个元素
    // 如果成功插入则返回真
    public boolean enQueue(int value) {
        if (isFull()) return false;
        Node temp = new Node(value);
        if (isEmpty()) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        count++;
        return true;
    }

    // 从循环队列中删除一个元素
    // 如果成功删除则返回真
    public boolean deQueue() {
        if (isEmpty()) return false;
        head = head.next;
        count--;
        return true;
    }

    // 从队首获取元素
    // 如果队列为空返回 -1
    public int Front() {
        return count == 0 ? -1 : head.value;
    }

    // 获取队尾元素
    // 如果队列为空返回 -1
    public int Rear() {
        return count == 0 ? -1 : tail.value;
    }

    // 检查循环队列是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 检查循环队列是否已满
    public boolean isFull() {
        return count == capacity;
    }

    // 单向链表结构
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
}

package com.github.zhgxun.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 面试题59 - II. 队列的最大值
 * <p>
 * https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 */
public class MaxQueue {

    Queue<Integer> queue = new LinkedList<>();
    Deque<Integer> deque = new LinkedList<>();

    public MaxQueue() {

    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (deque.size() > 0 && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.offerLast(value);
    }

    public int pop_front() {
        int tmp = queue.size() > 0 ? queue.poll() : -1;
        if (deque.size() > 0 && deque.peek().equals(tmp)) {
            deque.poll();
        }
        return tmp;
    }
}

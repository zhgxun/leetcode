package com.github.zhgxun.leetcode.stack;

import java.util.Stack;

/**
 * 155. 最小栈
 * <p>
 * https://leetcode-cn.com/problems/min-stack/
 */
public class MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {
    }

    public void push(int x) {
        stack.push(x);
        minStack.push(minStack.isEmpty() ? x : (minStack.peek() > x ? x : minStack.peek()));
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            if (!minStack.isEmpty()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

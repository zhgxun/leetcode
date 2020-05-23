package com.github.zhgxun.leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 155. 最小栈
 * <p>
 * https://leetcode-cn.com/problems/min-stack/
 */
public class MinStack {

    public static void main(String[] args) {
        System.out.println(getRow(0));
        System.out.println(getRow(1));
        System.out.println(getRow(2));
        System.out.println(getRow(3));
        System.out.println(getRow(4));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> dp = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i < rowIndex + 1; i++) {
            dp.add(0);
        }
        for (int i = 0; i <= rowIndex; i++) {
            dp.set(i, 1);
            for (int j = i; j > 1; j--) {
                dp.set(j - 1, dp.get(j - 1) + dp.get(j - 2));
            }
        }

        return dp;
    }

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

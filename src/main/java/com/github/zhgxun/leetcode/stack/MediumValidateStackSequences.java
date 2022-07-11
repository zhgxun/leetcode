package com.github.zhgxun.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.cn/problems/validate-stack-sequences/
public class MediumValidateStackSequences {

    public static void main(String[] args) {
        MediumValidateStackSequences object = new MediumValidateStackSequences();
        System.out.println(object.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 1, 2}));
    }

    // pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int j = 0;
        for (int i : pushed) {
            stack.push(i);

            while (!stack.isEmpty() && j < popped.length && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }
}

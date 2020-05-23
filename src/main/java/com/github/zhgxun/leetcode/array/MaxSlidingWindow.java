package com.github.zhgxun.leetcode.array;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 239. 滑动窗口最大值
 * <p>
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxSlidingWindow().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        //System.out.println(Arrays.toString(new MaxSlidingWindow().maxSlidingWindow(new int[]{1, -1}, 1)));
        //System.out.println(Arrays.toString(new MaxSlidingWindow().maxSlidingWindow(new int[]{7, 2, 4}, 2)));
    }

    ArrayDeque<Integer> deque = new ArrayDeque<>();

    // 2. 维护容量为 k 的最大堆 复杂度是 对数级别 需要注意顺序先清理队列在添加元素
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k * nums.length == 0) {
            return new int[0];
        }

        int[] res = new int[nums.length - k + 1];
        int index = 0;

        for (int i = 0; i < k; i++) {
            clean(nums, i, k);
            deque.addLast(i);
        }
        res[index++] = nums[deque.getFirst()];
        for (int i = k; i < nums.length; i++) {
            clean(nums, i, k);
            deque.addLast(i);
            res[index++] = nums[deque.getFirst()];
        }

        return res;
    }

    private void clean(int[] numbers, int i, int k) {
        // 队列满则清除队首元素
        if (!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }

        // 顺便移除比当前元素小的元素, 他不可能是最大的元素
        while (!deque.isEmpty() && numbers[i] > numbers[deque.getLast()]) {
            deque.removeLast();
        }
    }
}

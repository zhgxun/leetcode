package com.github.zhgxun.leetcode;

import java.util.PriorityQueue;

public class KthLargest {

    public static void main(String[] args) {
    }

    private final PriorityQueue<Integer> heap;
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>(k);
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) heap.poll();
        }
    }

    public int add(int val) {
        heap.add(val);
        if (heap.size() > k) heap.poll();
        return heap.isEmpty() ? -1 : heap.peek();
    }
}

package com.github.zhgxun.leetcode.array;

import java.util.PriorityQueue;

public class KthLargest {

    public static void main(String[] args) {
        KthLargest o = new KthLargest(3, new int[]{4, 5, 8, 2, 10, 2});
        System.out.println(o.queue);
    }

    private PriorityQueue<Integer> queue;
    private Integer k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>(k);
        for (Integer i : nums) {
            add(i);
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.offer(val);
        } else if (queue.peek() < val) {
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }
}

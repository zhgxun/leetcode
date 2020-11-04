package com.github.zhgxun.leetcode.stack;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Ugly {
    public final int[] numbers = new int[1690];

    public Ugly() {
        HashSet<Long> set = new HashSet<>((int) (1690 / 0.75 + 1));
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.add(1L);
        set.add(1L);

        long curUgly, newUgly;
        int[] baseList = new int[]{2, 3, 5};
        for (int i = 0; i < 1690; i++) {
            // 取出当前最小的丑数
            curUgly = heap.poll();
            numbers[i] = (int) curUgly;
            for (int base : baseList) {
                newUgly = base * curUgly;
                if (!set.contains(newUgly)) {
                    set.add(newUgly);
                    heap.add(newUgly);
                }
            }
        }
    }
}

package com.github.zhgxun.leetcode.thread;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main3 {
    private final Deque<Integer> deque = new ArrayDeque<>();
    private volatile boolean odd = true;
    private volatile boolean even = true;

    public static void main(String[] args) {
        Main3 o = new Main3();

        new Thread(() -> {
            int count = 10;
            while (count > 0) {
                o.deque.addFirst(count--);
            }
        }).start();

        new Thread(() -> {
            while (!o.deque.isEmpty()) {
                while (o.even) {
                }

                System.out.println(Thread.currentThread().getName() + ": " + o.deque.removeFirst());
                o.even = true;
                o.odd = false;
            }
        }).start();

        new Thread(() -> {
            while (!o.deque.isEmpty()) {
                while (o.odd) {
                }

                System.out.println(Thread.currentThread().getName() + ": " + o.deque.removeFirst());
                o.odd = true;
                o.even = false;
            }
        }).start();

        // 先触发一个执行
        o.even = false;
    }
}

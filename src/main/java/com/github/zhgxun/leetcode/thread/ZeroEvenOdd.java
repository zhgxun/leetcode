package com.github.zhgxun.leetcode.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class ZeroEvenOdd {
    private int n;
    private final BlockingDeque<Integer> zero = new LinkedBlockingDeque<>(1);
    private final BlockingDeque<Integer> even = new LinkedBlockingDeque<>(1);
    private final BlockingDeque<Integer> odd = new LinkedBlockingDeque<>(1);

    public ZeroEvenOdd(int n) {
        this.n = n;
        zero.add(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            printNumber.accept(zero.take());
            if (i % 2 == 0) {
                even.add(i);
            } else {
                odd.add(i);
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            printNumber.accept(even.take());
            zero.add(0);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            printNumber.accept(odd.take());
            zero.add(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd object = new ZeroEvenOdd(5);
        IntConsumer consumer = new IntConsumer();
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(() -> {
            try {
                object.zero(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.submit(() -> {
            try {
                object.even(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.submit(() -> {
            try {
                object.odd(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.shutdown();
    }
}

class IntConsumer {
    public void accept(int x) {
        System.out.print(x);
    }
}

package com.github.zhgxun.leetcode.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicFoo {
    private final AtomicInteger atomic = new AtomicInteger(0);

    public AtomicFoo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        atomic.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (atomic.get() != 1) {
        }
        printSecond.run();
        atomic.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (atomic.get() != 2) {
        }
        printThird.run();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                AtomicFoo foo = new AtomicFoo();
                StringBuilder sb = new StringBuilder();

                CountDownLatch latch = new CountDownLatch(3);
                new Thread(() -> {
                    try {
                        foo.second(() -> sb.append("second"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }).start();
                new Thread(() -> {
                    try {
                        foo.first(() -> sb.append("first"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }).start();
                new Thread(() -> {
                    try {
                        foo.third(() -> sb.append("third"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }).start();

                // 最后输出结果
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(sb.toString());
            }).start();
        }
    }
}

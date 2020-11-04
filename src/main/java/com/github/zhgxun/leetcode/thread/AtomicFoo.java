package com.github.zhgxun.leetcode.thread;

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
}

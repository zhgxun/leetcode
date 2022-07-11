package com.github.zhgxun.leetcode.array;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBlockingQueueDemo {

    private final AtomicInteger atomicInteger = new AtomicInteger();
    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueueDemo demo = new ArrayBlockingQueueDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int fixNum = 10000000;
        for (int i = 0; i < fixNum; i++) {
            executorService.submit(() -> {
                if (demo.atomicInteger.incrementAndGet() == fixNum) {
                    demo.countDownLatch.countDown();
                }
            });
        }
        demo.countDownLatch.await();
        executorService.shutdown();
        System.out.println(demo.atomicInteger.get());
    }
}

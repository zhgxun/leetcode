package com.github.zhgxun.leetcode.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private final Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) {
        SemaphoreDemo demo = new SemaphoreDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                demo.semaphore.release();
                try {
                    demo.semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //demo.semaphore.release();
                System.out.println(Thread.currentThread().getName());
            });
        }

        executorService.shutdown();
    }
}

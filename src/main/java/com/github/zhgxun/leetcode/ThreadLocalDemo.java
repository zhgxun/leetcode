package com.github.zhgxun.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {

    private final ThreadLocal<Integer> local = new ThreadLocal<>();

    public static void main(String[] args) {
        ThreadLocalDemo localDemo = new ThreadLocalDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 1; i <= 1; i++) {
            int finalI = i;
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " set :" + finalI);
                localDemo.local.set(finalI);

                System.out.println(Thread.currentThread().getName() + " set :" + finalI * 10);
                localDemo.local.set(finalI * 10);

                localDemo.local.remove();

                System.out.println(Thread.currentThread().getName() + " get:  " + localDemo.local.get());
            });
        }

        executorService.shutdown();
    }
}

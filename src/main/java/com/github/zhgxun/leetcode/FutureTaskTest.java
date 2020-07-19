package com.github.zhgxun.leetcode;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {

    public static void main(String[] args) throws Exception {
        Callable<LocalDateTime> callable = () -> {
            TimeUnit.SECONDS.sleep(1);
            return LocalDateTime.now();
        };

        FutureTask<LocalDateTime> task;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            task = new FutureTask<>(callable);
            executorService.submit(task);
            System.out.println(task.get());
        }

        executorService.shutdown();
    }
}

package com.github.zhgxun.leetcode.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FizzBuzz {

    private final Semaphore fizzSemaphore = new Semaphore(0);
    private final Semaphore buzzSemaphore = new Semaphore(0);
    private final Semaphore fizzBuzzSemaphore = new Semaphore(0);
    private final Semaphore numberSemaphore = new Semaphore(1);

    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                fizzSemaphore.acquire();
                printFizz.run();
                numberSemaphore.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                buzzSemaphore.acquire();
                printBuzz.run();
                numberSemaphore.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            fizzBuzzSemaphore.acquire();
            printFizzBuzz.run();
            numberSemaphore.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            numberSemaphore.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzzSemaphore.release();
            } else if (i % 5 == 0) {
                buzzSemaphore.release();
            } else if (i % 3 == 0) {
                fizzSemaphore.release();
            } else {
                printNumber.accept(i);
                numberSemaphore.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final FizzBuzz fizzBuzz = new FizzBuzz(15);
        final IntConsumer consumer = new IntConsumer();
        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fizzBuzz.number(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
    }
}

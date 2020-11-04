package com.github.zhgxun.leetcode.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzzCon {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition fizzCondition = lock.newCondition();
    private final Condition buzzCondition = lock.newCondition();
    private final Condition fizzBuzzCondition = lock.newCondition();
    private final Condition numberCondition = lock.newCondition();
    private volatile boolean state = false;

    private int n;

    public FizzBuzzCon(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            lock.lock();
            try {
                if (i % 5 != 0) {
                    while (!state) {
                        fizzCondition.await();
                    }
                    printFizz.run();
                    state = false;
                    numberCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            lock.lock();
            try {
                if (i % 3 != 0) {
                    while (!state) {
                        buzzCondition.await();
                    }
                    printBuzz.run();
                    state = false;
                    numberCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            lock.lock();
            try {
                while (!state) {
                    fizzBuzzCondition.await();
                }
                printFizzBuzz.run();
                state = false;
                numberCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            lock.lock();
            try {
                while (state) {
                    numberCondition.await();
                }
                if (i % 3 == 0 && i % 5 == 0) {
                    state = true;
                    fizzBuzzCondition.signal();
                } else if (i % 5 == 0) {
                    state = true;
                    buzzCondition.signal();
                } else if (i % 3 == 0) {
                    state = true;
                    fizzCondition.signal();
                } else {
                    printNumber.accept(i);
                    numberCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final FizzBuzzCon fizzBuzz = new FizzBuzzCon(15);
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

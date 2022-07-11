package com.github.zhgxun.leetcode.thread;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private final Deque<Integer> deque = new ArrayDeque<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Main object = new Main();

        P p = new P(100, object.deque);

        // 生产者启动后往队列中放入一串数字
        p.start();
        // 消费者并发一个线程打印奇数, 一个打印偶数
//        C c1 = new C(object.deque, object.lock, object.condition, 0);
//        C c2 = new C(object.deque, object.lock, object.condition, 1);
//        c1.start();
//        c2.start();
//
//        p.join();
//        c1.join();
//        c2.join();
        D d1 = new D(object.deque, 0, object);
        D d2 = new D(object.deque, 1, object);
        d1.start();
        d2.start();

        p.join();
        d1.join();
        d2.join();
    }
}

// 生产者
class P extends Thread {
    private final int count;
    private final Deque<Integer> deque;

    P(int count, Deque<Integer> deque) {
        this.count = count;
        this.deque = deque;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= count) {
            deque.addFirst(i++);
        }
    }
}

// 消费者
class C extends Thread {
    private final Deque<Integer> deque;
    private final ReentrantLock lock;
    private final Condition condition;
    private final int id;

    C(Deque<Integer> deque, ReentrantLock lock, Condition condition, int id) {
        this.deque = deque;
        this.lock = lock;
        this.condition = condition;
        this.id = id;
    }

    @Override
    public void run() {
        while (!deque.isEmpty()) {
            lock.lock();
            try {
                if (!deque.isEmpty() && deque.peekFirst() % 2 == id) {
                    System.out.println(Thread.currentThread().getName() + ": " + deque.removeFirst());
                    condition.signalAll();
                } else {
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

class D extends Thread {
    private final Deque<Integer> deque;
    private final int id;
    private final Object object;

    D(Deque<Integer> deque, int id, Object object) {
        this.deque = deque;
        this.id = id;
        this.object = object;
    }

    @Override
    public void run() {
        while (!deque.isEmpty()) {
            synchronized (object) {
                while (!deque.isEmpty() && deque.peekFirst() % 2 != id) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 没有元素则退出执行
                if (deque.isEmpty()) break;

                System.out.println(Thread.currentThread().getName() + ": " + deque.removeFirst());
                object.notify();
            }
        }
    }
}
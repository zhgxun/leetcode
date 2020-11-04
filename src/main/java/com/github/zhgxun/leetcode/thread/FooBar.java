package com.github.zhgxun.leetcode.thread;

public class FooBar {
    private volatile boolean flag = false;
    private final Object object = new Object();
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    // 为真输出 foo
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (object) {
                while (!flag) object.wait();
                printFoo.run();
                object.notifyAll();
                flag = false;
            }
        }
    }

    // 为假输出 bar
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (object) {
                while (flag) object.wait();
                printBar.run();
                object.notifyAll();
                flag = true;
            }
        }
    }
}

package com.github.zhgxun.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * n 在 1000 及以内
 */
public class Fib {

    public static void main(String[] args) {
        System.out.println(new Fib().fib(44));
        System.out.println(new Fib().fib(5));
    }

    Map<Integer, Integer> cache = new HashMap<>();

    public int fib(int n) {
        return best(n);
    }

    /**
     * 暴力法-超时
     *
     * @param n
     * @return
     */
    public int force(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return force(n - 1) + force(n - 2);
    }

    /**
     * 使用存储空间优化的暴力法通过 已经很快
     *
     * @param n
     * @return
     */
    public int useCache(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int result = useCache(n - 1) + useCache(n - 2);
        cache.put(n, result % 1000000007);
        return cache.get(n);
    }

    /**
     * 最优-只需要记录前两个变量 n-1 和 n-2 即可推算出第三个变量
     *
     * @param n
     * @return
     */
    public int best(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int first = 0;
        int two = 1;
        int three = 1;
        for (int i = 2; i <= n; i++) {
            three = (first + two) % 1000000007;
            first = two;
            two = three;
        }

        return three;
    }
}

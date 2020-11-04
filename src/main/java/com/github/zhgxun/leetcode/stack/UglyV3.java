package com.github.zhgxun.leetcode.stack;

import java.util.Set;
import java.util.TreeSet;

public class UglyV3 {

    private final Set<Integer> set = new TreeSet<>();

    public int nthUglyNumber(int n, int a, int b, int c) {
        int i = 1;
        while (i <= n) {
            set.add(a * i);
            set.add(b * i);
            set.add(c * i);
            i++;
        }
        return (int) set.toArray()[n - 1];
    }
}

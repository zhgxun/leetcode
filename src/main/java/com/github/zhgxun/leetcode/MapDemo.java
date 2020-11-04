package com.github.zhgxun.leetcode;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class MapDemo {

    public static void main(String[] args) {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 10, 0.0001);
        filter.put(1);
        System.out.println(filter.mightContain(1));
    }
}

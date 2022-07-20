package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/combinations/
public class MediumCombine {

    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfsCombine(n, k, 1, new ArrayList<>());
        return result;
    }

    private void dfsCombine(int n, int k, int index, List<Integer> res) {
        if (res.size() == k) {
            result.add(new ArrayList<>(res));
            return;
        }

        for (int i = index; i <= n; i++) {
            res.add(i);
            dfsCombine(n, k, i + 1, res);
            res.remove(res.size() - 1);
        }
    }
}

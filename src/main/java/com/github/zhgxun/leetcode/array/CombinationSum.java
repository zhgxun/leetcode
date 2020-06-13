package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * <p>
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(new CombinationSum().combinationSum(new int[]{2, 3, 5}, 8));
    }

    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return result;
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<>());
        return result;
    }

    public void dfs(int[] candidates, int left, int sum, List<Integer> res) {
        if (sum == 0) {
            result.add(new ArrayList<>(res));
            return;
        }

        for (int i = left; i < candidates.length; i++) {
            if (sum - candidates[i] < 0) break;
            // 组合总和2的判断条件
            // if (i > left && candidates[i - 1] == candidates[i]) continue;
            // dfs(candidates, i + 1, sum - candidates[i], res);
            res.add(candidates[i]);
            dfs(candidates, i, sum - candidates[i], res);
            res.remove(res.size() - 1);
        }
    }
}

package com.github.zhgxun.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 51. N皇后
 * <p>
 * https://leetcode-cn.com/problems/n-queens/
 */
public class NQueens {
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        System.out.println(nQueens.solveNQueens(8));
    }

    private final List<List<String>> result = new ArrayList<>();
    private final Set<Integer> colSet = new HashSet<>(); // 列
    private final Set<Integer> pieSet = new HashSet<>(); // 左对角线
    private final Set<Integer> naSet = new HashSet<>(); // 右对角线

    public List<List<String>> solveNQueens(int n) {
        dfs(n, new ArrayList<>(), 0);
        return result;
    }

    public void dfs(int n, List<Integer> res, int row) {
        if (row >= n) {
            getResult(res, n);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (colSet.contains(col) || pieSet.contains(row + col) || naSet.contains(row - col)) continue;
            colSet.add(col);
            pieSet.add(row + col);
            naSet.add(row - col);

            res.add(col);
            dfs(n, res, row + 1);
            res.remove(res.size() - 1);

            colSet.remove(col);
            pieSet.remove(row + col);
            naSet.remove(row - col);
        }
    }

    public void getResult(List<Integer> list, int n) {
        List<String> res = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            res.add(".".repeat(list.get(row)) + "Q" + ".".repeat(n - list.get(row) - 1));
        }
        result.add(res);
    }
}

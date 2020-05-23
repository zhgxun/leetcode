package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后
 * <p>
 * https://leetcode-cn.com/problems/n-queens/
 */
public class SolveNQueens {
    int[] rows;
    // "hill" diagonals
    int[] hills;
    // "dale" diagonals
    int[] dales;
    int n;
    // output
    List<List<String>> output = new ArrayList<>();
    // queens positions
    int[] queens;

    public boolean isNotUnderAttack(int row, int col) {
        int res = rows[col] + hills[row - col + 2 * n] + dales[row + col];
        return res == 0;
    }

    public void placeQueen(int row, int col) {
        queens[row] = col;
        rows[col] = 1;
        hills[row - col + 2 * n] = 1;  // "hill" diagonals
        dales[row + col] = 1;   //"dale" diagonals
    }

    public void removeQueen(int row, int col) {
        queens[row] = 0;
        rows[col] = 0;
        hills[row - col + 2 * n] = 0;
        dales[row + col] = 0;
    }

    public void addSolution() {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int col = queens[i];
            String sb = ".".repeat(Math.max(0, col))
                    + "Q"
                    + ".".repeat(Math.max(0, n - col - 1));
            solution.add(sb);
        }
        output.add(solution);
    }

    public void backtrack(int row) {
        for (int col = 0; col < n; col++) {
            if (isNotUnderAttack(row, col)) {
                placeQueen(row, col);
                if (row + 1 == n) { // if n queens are already placed
                    addSolution();
                } else { // if not proceed to place the rest
                    backtrack(row + 1);
                }
                // backtrack
                removeQueen(row, col);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        rows = new int[n];
        hills = new int[4 * n - 1];
        dales = new int[2 * n - 1];
        queens = new int[n];

        backtrack(0);
        return output;
    }
}

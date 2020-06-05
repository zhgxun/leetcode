package com.github.zhgxun.leetcode.array;

import java.util.HashMap;

/**
 * 36. 有效的数独
 * <p>
 * https://leetcode-cn.com/problems/valid-sudoku/
 */
public class IsValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        HashMap<Integer, Integer>[] cols = new HashMap[9];
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];

        for (int i = 0; i < 9; i++) {
            cols[i] = new HashMap<>();
            rows[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                char ch = board[col][row];
                if (ch == '.') {
                    continue;
                }
                cols[col].put((int) ch, cols[col].getOrDefault((int) ch, 0) + 1);
                rows[row].put((int) ch, rows[row].getOrDefault((int) ch, 0) + 1);
                int index = (col / 3) * 3 + row / 3; // 字数独的下标
                boxes[index].put((int) ch, boxes[index].getOrDefault((int) ch, 0) + 1);

                // 任何一个存在不唯一的则不是有效的数独
                if (cols[col].get((int) ch) > 1 || rows[row].get((int) ch) > 1 || boxes[index].get((int) ch) > 1) {
                    return false;
                }
            }
        }

        return true;
    }
}

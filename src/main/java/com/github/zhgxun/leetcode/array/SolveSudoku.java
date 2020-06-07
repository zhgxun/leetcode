package com.github.zhgxun.leetcode.array;

/**
 * 37. 解数独
 * <p>
 * https://leetcode-cn.com/problems/sudoku-solver/
 */
public class SolveSudoku {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solve(board);
    }

    public boolean solve(char[][] board) {
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                if (board[col][row] == '.') { // 尝试放入所有数字, 未去重
                    // 依次枚举数字
                    for (char c = '1'; c <= '9'; c++) {
                        // 该位置可以放置数字
                        if (isValid(board, col, row, c)) {
                            // 放置数字
                            board[col][row] = c;

                            // 数独是否解决
                            if (solve(board)) {
                                return true;
                            }

                            // 回溯该次放置重新放置下一个数字
                            board[col][row] = '.';
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    // 检查字符是否已存在在数独中
    private boolean isValid(char[][] board, int col, int row, char c) {
        for (int i = 0; i < 9; i++) {
            // 列不变检查行
            if (board[col][i] != '.' && board[col][i] == c) {
                return false;
            }
            // 行不变检查列
            if (board[i][row] != '.' && board[i][row] == c) {
                return false;
            }
            // 检查字数独
            int boxCol = (row / 3) * 3 + i / 3;
            int boxRow = (col / 3) * 3 + i % 3;
            if (board[boxCol][boxRow] != '.' && board[boxCol][boxRow] == c) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        int[][] cols = new int[9][9];
        ++cols[1][2];
        int[][] rows = new int[9][9];
        int[][] boxes = new int[9][9];
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                char ch = board[col][row];
                if (ch == '.') {
                    continue;
                }
                int n = ch - '1';
                int index = (col / 3) * 3 + row / 3;
                if (++cols[col][n] > 1 || ++rows[row][n] > 1 || ++boxes[index][n] > 1) {
                    return false;
                }
            }
        }

        return true;
    }
}

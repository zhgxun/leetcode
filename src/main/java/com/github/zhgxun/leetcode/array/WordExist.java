package com.github.zhgxun.leetcode.array;

/**
 * 79. 单词搜索
 * <p>
 * https://leetcode-cn.com/problems/word-search/
 */
public class WordExist {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                new char[]{'A', 'B', 'C', 'E'},
                new char[]{'S', 'F', 'C', 'S'},
                new char[]{'A', 'D', 'E', 'E'},
        };

        System.out.println(new WordExist().exist(board, "ABCCED"));
        System.out.println(new WordExist().exist(board, "SEE"));
        System.out.println(new WordExist().exist(board, "ABCB"));
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }

        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                if (dfs(board, word, col, row, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] board, String word, int col, int row, int index) {
        int colLen = board.length;
        int rowLen = board[0].length;
        if (col < 0 || col >= colLen || row < 0 || row >= rowLen) {
            return false;
        }

        if (board[col][row] == '*' || board[col][row] != word.charAt(index)) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        board[col][row] = '*';
        if (dfs(board, word, col - 1, row, index + 1)) {
            return true;
        }
        if (dfs(board, word, col, row + 1, index + 1)) {
            return true;
        }
        if (dfs(board, word, col + 1, row, index + 1)) {
            return true;
        }
        if (dfs(board, word, col, row - 1, index + 1)) {
            return true;
        }

        board[col][row] = word.charAt(index);
        return false;
    }
}

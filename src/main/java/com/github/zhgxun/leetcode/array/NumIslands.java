package com.github.zhgxun.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 200. 岛屿数量
 * <p>
 * https://leetcode-cn.com/problems/number-of-islands/
 */
public class NumIslands {

    public static void main(String[] args) {
        char[][] chars = new char[][]{
                new char[]{'1', '1', '1', '1', '0'},
                new char[]{'1', '1', '0', '1', '0'},
                new char[]{'1', '1', '0', '0', '0'},
                new char[]{'0', '0', '0', '0', '0'}
        };
//        System.out.println(chars.length); // 4
//        System.out.println(chars[0].length); // 5
//        System.out.println(new NumIslands().numIslands(chars));
//        char[][] grid = new char[][]{
//                new char[]{'1', '1', '0', '0', '0'},
//                new char[]{'1', '1', '0', '0', '0'},
//                new char[]{'0', '0', '1', '0', '0'},
//                new char[]{'0', '0', '0', '1', '1'}
//        };
        char[][] grid = new char[][]{new char[]{'1', '0', '1', '1', '0', '1', '1'}};
        //System.out.println(new NumIslands().numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int count = 0;
        int colLen = grid.length;
        int rowLen = grid[0].length;
        for (int col = 0; col < colLen; col++) {
            for (int row = 0; row < rowLen; row++) {
                System.out.println(Arrays.toString(grid[0]));
                if (grid[col][row] == '1') {
                    count++;
                    dfs(grid, col, row);
                }
            }
        }

        return count;
    }

    public void dfs(char[][] grid, int col, int row) {
        // 终止条件
        int colLen = grid.length;
        int rowLen = grid[0].length;
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen || grid[col][row] == '0') {
            return;
        }

        // 处理当前层
        grid[col][row] = '0';
        //System.out.println(Arrays.toString(grid[0]));

        // 处理下一层, 顺时针
        dfs(grid, col - 1, row);
        dfs(grid, col + 1, row);
        dfs(grid, col, row - 1);
        dfs(grid, col, row + 1);
        // 恢复当前层状态
    }

    int minStepCount = Integer.MAX_VALUE;

    public int minMutation(String start, String end, String[] bank) {
        dfs(new HashSet<>(), 0, start, end, bank);
        return (minStepCount == Integer.MAX_VALUE) ? -1 : minStepCount;
    }

    private void dfs(HashSet<String> step, int stepCount, String current, String end, String[] bank) {
        // ter
        if (current.equals(end)) {
            minStepCount = Math.min(stepCount, minStepCount);
        }

        // process
        for (String str : bank) { // 基因库
            int diff = 0;
            for (int i = 0; i < str.length(); i++) {
                // 在基因库中寻找只需要变化一次就再目标基因库中的基因
                if (current.charAt(i) != str.charAt(i)) { // 差异多余1个则丢弃
                    if (++diff > 1) {
                        break;
                    }
                }
            }

            if (diff == 1 && !step.contains(str)) {
                step.add(str);
                dfs(step, stepCount + 1, str, end, bank);
                step.remove(str);
            }
        }
    }
}

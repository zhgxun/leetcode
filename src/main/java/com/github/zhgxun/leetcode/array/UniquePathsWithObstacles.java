package com.github.zhgxun.leetcode.array;

/**
 * 63. 不同路径 II
 * <p>
 * https://leetcode-cn.com/problems/unique-paths-ii/
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        //System.out.println(new UniquePathsWithObstacles().uniquePathsWithObstacles(new int[][]{new int[]{0, 0, 0}, new int[]{0, 1, 0}, new int[]{0, 0, 0}}));
        System.out.println(new UniquePathsWithObstacles().uniquePathsWithObstacles(new int[][]{new int[]{0, 1}, new int[]{1, 1}, new int[]{0, 0}}));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int col = obstacleGrid.length;
        int row = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // 初始值走1步
        obstacleGrid[0][0] = 1;

        // 初始化最开始的两条边
        for (int i = 1; i < row; i++) {
            if (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) {
                obstacleGrid[0][i] = 1;
            } else {
                obstacleGrid[0][i] = 0;
            }
        }
        for (int i = 1; i < col; i++) {
            if (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) {
                obstacleGrid[i][0] = 1;
            } else {
                obstacleGrid[i][0] = 0;
            }
        }

        // 动态规划处理
        for (int i = 1; i < col; i++) {
            for (int j = 1; j < row; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }

        return obstacleGrid[col - 1][row - 1];
    }
}

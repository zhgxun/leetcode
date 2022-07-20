package com.github.zhgxun.leetcode.array;

// https://leetcode.cn/problems/spiral-matrix-ii/
public class MediumGenerateMatrix {

    public static void main(String[] args) {
        MediumGenerateMatrix object = new MediumGenerateMatrix();
        int[][] matrix = object.generateMatrix(3);
        object.print(matrix);
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;

        int num = 1;

        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                result[top][i] = num++;
            }
            for (int i = top + 1; i <= bottom; i++) {
                result[i][right] = num++;
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i >= left; i--) {
                    result[bottom][i] = num++;
                }
                for (int i = bottom - 1; i > top; i--) {
                    result[i][left] = num++;
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }

        return result;
    }

    public void print(int[][] matrix) {
        for (int col = 0; col < matrix.length; col++) {
            for (int row = 0; row < matrix[0].length; row++) {
                System.out.printf("%d ", matrix[col][row]);
            }
            System.out.println();
        }
    }
}

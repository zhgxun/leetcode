package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/spiral-matrix/
public class MediumSpiralOrder {

    public static void main(String[] args) {
        MediumSpiralOrder object = new MediumSpiralOrder();
        System.out.println(object.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        while (left <= right && top <= bottom) {
            // 打印顶行
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            // 打印右列
            for (int i = top + 1; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            if (top < bottom && left < right) {
                // 打印底行
                for (int i = right - 1; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                // 打印左列
                for (int i = bottom - 1; i > top; i--) {
                    result.add(matrix[i][left]);
                }
            }

            left++;
            right--;
            top++;
            bottom--;
        }

        return result;
    }
}

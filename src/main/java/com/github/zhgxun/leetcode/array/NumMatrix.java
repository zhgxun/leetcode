package com.github.zhgxun.leetcode.array;

public class NumMatrix {

    public int integerBreak(int n) {
        if (n < 2) return 1;
        int times = n / 3;
        int remain = n % 3;
        if (remain == 0) return (int) Math.pow(3, times);
        else if (remain == 1) return (int) Math.pow(3, times - 1) * 4;
        else return (int) Math.pow(3, times) * 2;
    }

    public int thirdMax(int[] nums) {
        long one = nums[0], two = Long.MIN_VALUE, three = Long.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == one || nums[i] == two || nums[i] == three) continue;
            if (nums[i] > one) {
                three = two;
                two = one;
                one = nums[i];
            } else if (nums[i] > two) {
                three = two;
                two = nums[i];
            } else {
                three = nums[i];
            }
        }

        return three == Long.MIN_VALUE ? (int) one : (int) three;
    }

    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;

        int colLen = matrix.length;
        int rowLen = matrix[0].length;
        sum = new int[colLen][rowLen];
        for (int col = 0; col < colLen; col++) {
            sum[col][0] = matrix[col][0];
            for (int row = 1; row < rowLen; row++) {
                sum[col][row] = sum[col][row - 1] + matrix[col][row];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int total = 0;
        for (int col = row1; col <= row2; col++) {
            total += sum[col][col2] - (col >= 1 ? sum[col][col1 - 1] : 0);
        }
        return total;
    }
}

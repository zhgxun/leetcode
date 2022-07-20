package com.github.zhgxun.leetcode.array;

// https://leetcode.cn/problems/search-a-2d-matrix/
public class MediumSearchMatrix {

    public static void main(String[] args) {

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int val = matrix[mid / matrix[0].length][mid % matrix[0].length];
            if (val == target) {
                return true;
            } else if (val > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}

package com.github.zhgxun.leetcode.array;

import java.util.Arrays;

/**
 * 912. 排序数组
 * <p>
 * https://leetcode-cn.com/problems/sort-an-array/
 */
public class SortArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SortArray().sortArray(new int[]{4, 3, 5, 1, 2})));
        System.out.println(Arrays.toString(new SortArray().sortArrayV2(new int[]{4, 3, 5, 1, 2})));
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public int[] sortArrayV2(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSort(int[] a, int left, int right) {
        if (left > right) return;
        int index = left; // 记录数组移动的左边界位置
        for (int i = left; i < right; i++) {
            if (a[i] < a[right]) { // 以右边为界
                int temp = a[index];
                a[index] = a[i];
                a[i] = temp;
                index++;
            }
        }

        int temp = a[index];
        a[index] = a[right];
        a[right] = temp;

        quickSort(a, left, index - 1);
        quickSort(a, index + 1, right);
    }

    public void mergeSort(int[] a, int left, int right) {
        if (left >= right) return;
        int middle = (left + right) / 2;
        mergeSort(a, left, middle);
        mergeSort(a, middle + 1, right);
        merge(a, left, middle, right);
    }

    public void merge(int[] a, int left, int middle, int right) {
        int[] temp = new int[right - left + 1];
        int k = 0;
        int i = left;
        int j = middle + 1;
        while (i <= middle && j <= right) {
            temp[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        }

        while (i <= middle) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];

        System.arraycopy(temp, 0, a, left, temp.length);
    }
}

package com.github.zhgxun.leetcode.math;

import java.util.Arrays;

// https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/
public class MediumConstructArr {

    public static void main(String[] args) {
        MediumConstructArr object = new MediumConstructArr();
        System.out.println(Arrays.toString(object.constructArr(new int[]{1, 2, 3, 4, 5})));
    }

    // 空间复杂度为 O(n) 的解法
    public int[] constructArr(int[] a) {
        if (a.length == 0) {
            return new int[]{};
        }

        int[] result = new int[a.length];
        result[0] = 1;
        for (int i = 1; i < a.length; i++) {
            result[i] = result[i - 1] * a[i - 1];
        }

        int right = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            right *= a[i + 1];
            result[i] = result[i] * right;
        }
        return result;
    }
}

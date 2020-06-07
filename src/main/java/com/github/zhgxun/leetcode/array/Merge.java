package com.github.zhgxun.leetcode.array;

import com.github.zhgxun.leetcode.ArrayUtil;

import java.util.Arrays;

/**
 * 56. 合并区间
 * <p>
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class Merge {

    public static void main(String[] args) {
        ArrayUtil.print(new Merge().merge(new int[][]{
                new int[]{8, 10},
                new int[]{1, 3},
                new int[]{2, 6},
                new int[]{15, 18},
        }));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[][] res = new int[intervals.length][2];
        int index = -1;
        for (int[] interval : intervals) {
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }
}

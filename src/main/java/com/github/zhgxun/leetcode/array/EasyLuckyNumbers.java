package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/lucky-numbers-in-a-matrix/
public class EasyLuckyNumbers {

    public static void main(String[] args) {

    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        // 行的最小值集合
        for (int col = 0; col < matrix.length; col++) {
            int minVal = Integer.MAX_VALUE;
            for (int row = 0; row < matrix[0].length; row++) {
                minVal = Math.min(minVal, matrix[col][row]);
            }
            map.put(minVal, 1);
        }

        // 列的最大值集合
        for (int row = 0; row < matrix[0].length; row++) {
            int maxVal = Integer.MIN_VALUE;
            for (int col = 0; col < matrix.length; col++) {
                maxVal = Math.max(maxVal, matrix[col][row]);
            }
            if (map.containsKey(maxVal)) {
                result.add(maxVal);
                break;
            }
        }

        return result;
    }
}

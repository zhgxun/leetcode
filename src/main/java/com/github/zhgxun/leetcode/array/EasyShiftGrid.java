package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/shift-2d-grid/
public class EasyShiftGrid {

    public static void main(String[] args) {
        EasyShiftGrid object = new EasyShiftGrid();
        System.out.println(object.shiftGrid(new int[][]{{3, 8, 1, 9}, {19, 7, 2, 5}, {4, 6, 11, 10}, {12, 0, 21, 13}}, 1));
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> result = new ArrayList<>();
        for (int col = 0; col < grid.length; col++) {
            List<Integer> line = new ArrayList<>();
            for (int row = 0; row < grid[0].length; row++) {
                line.add(0);
            }
            result.add(line);
        }

        // 注意矩阵坐标的枚举方式
        // grid[col][row]
        // 一维数组的角度索引为 index = col * grid[0].length + row
        // 映射为二位矩阵时的索引为 index = (col * grid[0].length + row) % (grid.length * grid[0].length)
        // col -> index / grid[0].length
        // row -> index % grid[0].length
        for (int col = 0; col < grid.length; col++) {
            for (int row = 0; row < grid[0].length; row++) {
                int index = (col * grid[0].length + row + k) % (grid.length * grid[0].length);
                result.get(index / grid[0].length).set(index % grid[0].length, grid[col][row]);
            }
        }

        return result;
    }
}

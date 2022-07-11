package com.github.zhgxun.leetcode.array;

/**
 * 547. 朋友圈
 * <p>
 * https://leetcode-cn.com/problems/friend-circles/
 */
public class FindCircleNum {

    public static void main(String[] args) {
        int[][] m = new int[][]{
                new int[]{1, 1, 0},
                new int[]{1, 1, 0},
                new int[]{0, 0, 1}
        };
        System.out.println(new FindCircleNum().findCircleNum(m));
    }

    public int findCircleNum(int[][] M) {
        UnionFind unionFind = new UnionFind(M.length);
        for (int col = 0; col < M.length; col++) {
            for (int row = 0; row < M[0].length; row++) {
                if (M[col][row] == 1) {
                    unionFind.union(col, row);
                }
            }
        }

        return unionFind.getCount();
    }
}

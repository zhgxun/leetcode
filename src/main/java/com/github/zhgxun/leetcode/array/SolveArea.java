package com.github.zhgxun.leetcode.array;

/**
 * 130. 被围绕的区域
 * <p>
 * https://leetcode-cn.com/problems/surrounded-regions/
 */
public class SolveArea {

    // 转化为找边界的O连通的最大区域
    // 用并查集将其连接即可
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int colLen = board.length;
        int rowLen = board[0].length;

        UnionFind unionFind = new UnionFind(colLen * rowLen + 1);

        int val = colLen * rowLen; // 边界合并为一个节点的值, 不重复即可
        for (int col = 0; col < colLen; col++) {
            for (int row = 0; row < rowLen; row++) {
                // 连通 O
                if (board[col][row] == 'O') {
                    int curVal = col * rowLen + row;
                    if (col == 0 || col == colLen - 1 || row == 0 || row == rowLen - 1) {
                        // 边界上的O合并到一个连通区域中
                        unionFind.union(curVal, val);
                    } else {
                        // 其他区域合并到自己的区域即可
                        if (board[col - 1][row] == 'O') { // 上
                            unionFind.union(curVal, (col - 1) * rowLen + row);
                        }
                        if (row + 1 < rowLen && board[col][row + 1] == 'O') { // 右
                            unionFind.union(curVal, col * rowLen + row + 1);
                        }
                        if (col + 1 < colLen && board[col + 1][row] == 'O') { // 下
                            unionFind.union(curVal, (col + 1) * rowLen + row);
                        }
                        if (board[col][row - 1] == 'O') { // 左
                            unionFind.union(curVal, col * rowLen + row - 1);
                        }
                    }
                }
            }
        }

        // 再次遍历矩阵, 不具连通性的直接替换即可
        for (int col = 0; col < colLen; col++) {
            for (int row = 0; row < rowLen; row++) {
                if (unionFind.connected(col * rowLen + row, val)) {
                    board[col][row] = 'O';
                } else {
                    board[col][row] = 'X';
                }
            }
        }
    }

    public static class UnionFind {
        private final int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            parent[rootP] = rootQ;
        }
    }
}

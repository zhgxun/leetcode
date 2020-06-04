package com.github.zhgxun.leetcode;

/**
 * 并查集 或者叫 DisjointSet
 */
public class UnionFind {

    // 记录连通分量
    private int count;

    // 记录节点
    private final int[] parent;
    // 记录节点的重量
    private final int[] weight;

    // 初始化并查集
    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    // 将p和q连通
    // 如果不处理路径, 最坏时退化为链表, 时间复杂度变更为 O(n)
    // 处理维持一颗相对平衡的树, 时间负责度可以保持在 O(logN) 对数级别
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        // 看重量拼接, 小树拼接到大树的下面
        if (weight[rootP] > weight[rootQ]) { // rootQ 拼接在树 rootP 的下面
            parent[rootQ] = rootP;
            // 计算根 rootP 的重量
            weight[rootP] += weight[rootQ];
        } else { // rootP 拼接在 rootQ 的下面
            parent[rootP] = rootQ;
            weight[rootQ] += weight[rootP];
        }
        // 连通后分量减少
        count--;
    }

    // 判断p和q是否连通
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 查找x的根节点
    public int find(int x) {
        // 根节点是 parent[x] = x
        while (parent[x] != x) {
            // 路径压缩, 因为根的根始终是自己所以可以快进
            // 因此可以跳跃任意节点
            // 最终直接就是 parent[x] = x 两端相等
            parent[x] = parent[parent[parent[x]]];

            // 迭代上一个父节点
            x = parent[x];
        }

        return x;
    }

    // 最后的连通分量数量
    public int getCount() {
        return this.count;
    }
}

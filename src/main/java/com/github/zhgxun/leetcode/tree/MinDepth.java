package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 111. 二叉树的最小深度
 * <p>
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class MinDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(new MinDepth().minDepth(root));
    }

    public int minDepth(TreeNode root) {
        // 没有节点节点数为0
        if (root == null) {
            return 0;
        }

        // 当前节点无子节点节点数为1
        if (root.left == null && root.right == null) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        // 左子树的最少节点数
        if (root.left != null) {
            min = Math.min(minDepth(root.left), min);
        }
        // 右子树的最少节点数
        if (root.right != null) {
            min = Math.min(minDepth(root.right), min);
        }

        // 最终深度是节点数目加1
        return min + 1;
    }
}

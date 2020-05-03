package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 543. 二叉树的直径
 * <p>
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 * <p>
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 */
public class DiameterOfBinaryTree {

    int maxDept = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return maxDept;
        }
        maxDept = 1;
        dept(root);
        return maxDept - 1;
    }

    // 每个节点左节点个数和右节点个数之和
    public int dept(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dept(root.left);
        int right = dept(root.right);
        maxDept = Math.max(maxDept, left + right + 1);
        return Math.max(left, right) + 1;
    }
}

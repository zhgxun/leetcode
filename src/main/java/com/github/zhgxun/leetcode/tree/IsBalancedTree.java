package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 面试题 04.04. 检查平衡性
 * <p>
 * https://leetcode-cn.com/problems/check-balance-lcci/
 * <p>
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 */
public class IsBalancedTree {

    // 递归思路
    // 递归求每颗子树的高度差
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftHeight = getHeight(root.left, 0);
        int rightHeight = getHeight(root.right, 0);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    // 获取一个节点的子树高度差
    // 注意递归的点, 是求树的高度差
    private static int getHeight(TreeNode root, int height) {
        if (root == null) {
            return height;
        }
        // 左子树高度
        int leftHeight = getHeight(root.left, height + 1);
        // 右子树高度
        int rightHeight = getHeight(root.right, height + 1);
        return Math.max(leftHeight, rightHeight);
    }
}

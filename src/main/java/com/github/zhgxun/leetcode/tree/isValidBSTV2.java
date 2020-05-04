package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 98. 验证二叉搜索树
 * <p>
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class isValidBSTV2 {

    // 二叉搜索树相当中序遍历是递增有序排序数组
    private long preValue = Long.MIN_VALUE;

    // 中序遍历的方式验证是否为二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return inOrder(root);
    }

    // 借助中序遍历的特性
    public boolean inOrder(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean result = inOrder(root.left);
        if (!result) {
            return false;
        }
        if (root.val <= preValue) {
            return false;
        }
        preValue = root.val;
        return inOrder(root.right);
    }
}

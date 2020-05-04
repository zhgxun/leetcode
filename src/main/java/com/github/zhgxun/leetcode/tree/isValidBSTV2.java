package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 98. 验证二叉搜索树
 * <p>
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class isValidBSTV2 {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        TreeNode maxLeft = root.left;
        while (maxLeft != null && maxLeft.right != null) {
            maxLeft = maxLeft.right;
        }
        TreeNode minRight = root.right;
        while (minRight != null && minRight.left != null) {
            minRight = minRight.left;
        }

        boolean result = (maxLeft == null || maxLeft.val < root.val) && (minRight == null || minRight.val > root.val);

        return result && isValidBST(root.left) && isValidBST(root.right);
    }
}

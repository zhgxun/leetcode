package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;
import com.github.zhgxun.leetcode.TreeNodeUtil;

/**
 * 538. 把二叉搜索树转换为累加树
 * <p>
 * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 * <p>
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 */
public class ConvertBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        TreeNodeUtil.inOrder(new ConvertBST().convertBST(root));
    }

    private int sum = 0;

    // 后续遍历记录后续和
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        order(root);

        return root;
    }

    void order(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.right != null) {
            order(root.right);
        }
        sum += root.val;
        root.val = sum;
        if (root.left != null) {
            order(root.left);
        }
    }
}

package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;
import com.github.zhgxun.leetcode.TreeNodeUtil;

/**
 * 226. 翻转二叉树
 * <p>
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * <p>
 * 翻转一棵二叉树。
 */
public class InvertTree {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(4);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(1);
        tree.left.right = new TreeNode(3);
        tree.right = new TreeNode(7);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(9);

        System.out.println(TreeNodeUtil.preOrderTraversal(tree));
        System.out.println(TreeNodeUtil.inOrderTraversal(new InvertTree().invertTree(tree)));
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

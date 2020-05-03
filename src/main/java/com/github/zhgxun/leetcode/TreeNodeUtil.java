package com.github.zhgxun.leetcode;

public class TreeNodeUtil {

    private TreeNodeUtil() {
    }

    public static void PreOrder(TreeNode root) {

    }

    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrder(root.left);
        }
        System.out.println(root.val);
        if (root.right != null) {
            inOrder(root.right);
        }
    }

    public static void afterOrder(TreeNode root) {

    }
}

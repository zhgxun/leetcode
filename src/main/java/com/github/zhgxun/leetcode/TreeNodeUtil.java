package com.github.zhgxun.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeUtil {

    private TreeNodeUtil() {
    }

    // 前序遍历
    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        preOrder(root, resultList);
        return resultList;
    }

    public static void preOrder(TreeNode root, List<Integer> resultList) {
        if (root == null) {
            return;
        }
        resultList.add(root.val);
        preOrder(root.left, resultList);
        preOrder(root.right, resultList);
    }

    // 中序遍历
    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        inOrder(root, resultList);
        return resultList;
    }

    public static void inOrder(TreeNode root, List<Integer> resultList) {
        if (root == null) {
            return;
        }
        inOrder(root.left, resultList);
        resultList.add(root.val);
        inOrder(root.right, resultList);
    }

    // 后序遍历
    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        postOrder(root, resultList);
        return resultList;
    }

    public static void postOrder(TreeNode root, List<Integer> resultList) {
        if (root == null) {
            return;
        }
        postOrder(root.left, resultList);
        postOrder(root.right, resultList);
        resultList.add(root.val);
    }
}

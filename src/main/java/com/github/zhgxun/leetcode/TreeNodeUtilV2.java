package com.github.zhgxun.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 迭代方式的树前序, 后序和中序遍历
 */
public class TreeNodeUtilV2 {

    // 前序遍历
    public static List<Integer> preOrderTraversal(TreeNode root) {
        LinkedList<Integer> resultList = new LinkedList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            TreeNode node = linkedList.pollLast();
            resultList.add(node.val);
            if (node.right != null) {
                linkedList.add(node.right);
            }
            if (node.left != null) {
                linkedList.add(node.left);
            }
        }

        return resultList;
    }

    // 中序遍历
    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            resultList.add(curr.val);
            curr = curr.right;
        }

        return resultList;
    }

    // 后序遍历
    public static List<Integer> postOrderTraversal(TreeNode root) {
        LinkedList<Integer> resultList = new LinkedList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            TreeNode node = linkedList.pollLast();
            resultList.addFirst(node.val);
            if (node.left != null) {
                linkedList.add(node.left);
            }
            if (node.right != null) {
                linkedList.add(node.right);
            }
        }

        return resultList;
    }
}

package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;
import com.github.zhgxun.leetcode.TreeNodeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * <p>
 * 给定一个二叉树，返回它的中序 遍历
 */
public class InOrderTraversal {

    public static void main(String[] args) {
        /**
         *       1
         *      /\
         *     2  3
         *       /\
         *      4 5
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(TreeNodeUtil.inOrderTraversal(root));
        System.out.println(new InOrderTraversal().inorderTraversal(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
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
}

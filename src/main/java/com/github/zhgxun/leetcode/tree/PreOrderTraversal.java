package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;
import com.github.zhgxun.leetcode.TreeNodeUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * <p>
 * 给定一个二叉树，返回它的 前序 遍历。
 */
public class PreOrderTraversal {

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

        System.out.println(TreeNodeUtil.preOrderTraversal(root));
        System.out.println(new PreOrderTraversal().preOrderTraversal(root));
    }

    public List<Integer> preOrderTraversal(TreeNode root) {
        LinkedList<Integer> resultList = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            // 取栈底元素
            TreeNode node = stack.pollLast();
            // 先取出来的元素在最前面
            resultList.add(node.val);
            // 需要先获取的元素先入栈
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }

        return resultList;
    }

}

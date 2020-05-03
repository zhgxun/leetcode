package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;
import com.github.zhgxun.leetcode.TreeNodeUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * <p>
 * 给定一个二叉树，返回它的 后序 遍历。
 */
public class PostOrderTraversal {

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

        System.out.println(TreeNodeUtil.postOrderTraversal(root));
        System.out.println(new PostOrderTraversal().postOrderTraversal(root));
    }

    // 递归解法很容易理解
    // 试试栈的解法
    public List<Integer> postOrderTraversal(TreeNode root) {
        LinkedList<Integer> resultList = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            // 栈底元素出栈
            TreeNode node = stack.pollLast();
            // 元素位于栈顶
            // 后续先出栈右子树, 后出栈左子树
            resultList.addFirst(node.val);
            // 左子树先入栈后出栈
            if (node.left != null) {
                stack.add(node.left);
            }
            // 右子树后入栈先出栈
            if (node.right != null) {
                stack.add(node.right);
            }
        }

        return resultList;
    }
}

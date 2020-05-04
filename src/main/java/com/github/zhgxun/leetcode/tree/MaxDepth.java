package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 104. 二叉树的最大深度
 * <p>
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 */
public class MaxDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new MaxDepth().maxDepth(root));
    }

    List<List<Integer>> levels = new ArrayList<>();

    // 层次遍历方式效率不高
    public int maxDepthV2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        inOrder(root, 0);
        return levels.size();
    }

    public void inOrder(TreeNode root, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(root.val);
        if (root.left != null) {
            inOrder(root.left, level + 1);
        }
        if (root.right != null) {
            inOrder(root.right, level + 1);
        }
    }

    // 递归解法, 根节点深度为0, 每次递归出一层则加1, 递归至根节点为最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}

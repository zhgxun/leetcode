package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 107. 二叉树的层次遍历 II
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 * <p>
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 */
public class LevelOrderBottom {

    List<List<Integer>> levels = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return levels;
        }
        helper(root, 0);
        // 倒序一次
        List<List<Integer>> resultList = new ArrayList<>();
        int size = levels.size();
        for (int i = size - 1; i >= 0; i--) {
            resultList.add(levels.get(i));
        }
        return resultList;
    }

    public void helper(TreeNode root, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(root.val);
        if (root.left != null) {
            helper(root.left, level + 1);
        }
        if (root.right != null) {
            helper(root.right, level + 1);
        }
    }
}

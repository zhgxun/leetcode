package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * <p>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class LevelOrder {

    // 迭代法效率不高
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> results = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                results.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            resultList.add(results);
        }

        return resultList;
    }

    List<List<Integer>> levels = new ArrayList<>();

    // 递归的效率比迭代要高
    public List<List<Integer>> levelOrderV2(TreeNode root) {
        if (root == null) {
            return levels;
        }

        helper(root, 0);
        return levels;
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

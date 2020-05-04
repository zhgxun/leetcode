package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层次遍历
 * <p>
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class ZigzagLevelOrder {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(new ZigzagLevelOrder().zigzagLevelOrder(root));
    }

    List<List<Integer>> levels = new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return levels;
        }
        preOrder(root, 0);
        // 偶数节点反转一次
        int size = levels.size();
        for (int i = 1; i < size; i += 2) {
            reverse(levels.get(i));
        }

        return levels;
    }

    public void preOrder(TreeNode root, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(root.val);
        if (root.left != null) {
            preOrder(root.left, level + 1);
        }
        if (root.right != null) {
            preOrder(root.right, level + 1);
        }
    }

    public void reverse(List<Integer> list) {
        int size = list.size();
        int i = 0, j = size - 1;
        for (; i < j; i++, j--) {
            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}

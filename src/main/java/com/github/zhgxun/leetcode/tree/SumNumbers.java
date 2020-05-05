package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.LinkedList;

/**
 * 129. 求根到叶子节点数字之和
 * <p>
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 * <p>
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 */
public class SumNumbers {

    private int sum = 0;

    public int sumNumbersV2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        order(root, new LinkedList<>());

        return sum;
    }

    public void order(TreeNode root, LinkedList<Integer> tempList) {
        if (root == null) {
            return;
        }
        tempList.add(root.val);
        if (root.left == null && root.right == null) {
            int num = 0;
            for (Integer i : tempList) {
                num = num * 10 + i;
            }
            sum += num;
        }
        order(root.left, tempList);
        order(root.right, tempList);
        tempList.removeLast();
    }

    // 优化后的解法, 直接计算出结果
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumOrder(root, 0);

        return sum;
    }

    public void sumOrder(TreeNode root, int total) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            total = total * 10 + root.val;
            sum += total;
        }
        sumOrder(root.left, total * 10 + root.val);
        sumOrder(root.right, total * 10 + root.val);
    }
}

package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值
 * <p>
 * https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor/
 * <p>
 * 给定二叉树的根节点 root，找出存在于不同节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 */
public class MaxAncestorDiff {

    int result = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, root.val, root.val);
        return result;
    }

    // 每次从根节点寻找两棵树的最大值和最小值, 他们之间的差值即为最大差值
    public void helper(TreeNode root, int max, int min) {
        if (root == null) {
            return;
        }
        max = Math.max(root.val, max);
        min = Math.min(root.val, min);
        // 到达叶子节点求最大差值
        if (root.left == null && root.right == null) {
            result = Math.max(result, Math.abs(min - max));
        }
        helper(root.left, max, min);
        helper(root.right, max, min);
    }
}

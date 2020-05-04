package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 617. 合并二叉树
 * <p>
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 * <p>
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 */
public class MergeTrees {

    // 递归合并
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // 如果一棵树为空则返回另一棵树, 返回的树作为上一节点的子节点
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        // 两棵树均不为空则进行值合并
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }
}

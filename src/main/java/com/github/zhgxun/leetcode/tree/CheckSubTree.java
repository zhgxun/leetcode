package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 面试题 04.10. 检查子树
 * <p>
 * https://leetcode-cn.com/problems/check-subtree-lcci/
 * <p>
 * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
 * <p>
 * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
 */
public class CheckSubTree {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);

        TreeNode t2 = t1.left;

        System.out.println(new CheckSubTree().checkSubTree(t1, t2));
    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }

        TreeNode tree = findTreeNode(t1, t2);
        if (tree == null) {
            return false;
        }

        return compare(tree, t2);
    }

    // 从树中寻找某个节点, 不存在时返回null
    private TreeNode findTreeNode(TreeNode source, TreeNode target) {
        if (source == null) {
            return null;
        }
        if (source.val == target.val) {
            return source;
        }
        if (source.left != null) {
            TreeNode find = findTreeNode(source.left, target);
            if (find != null) {
                return find;
            }
        }
        if (source.right != null) {
            return findTreeNode(source.right, target);
        }

        return null;
    }

    // 比较两棵树的所有节点值是否相同而非树本身是否相同
    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        return left.val == right.val && compare(left.left, right.left) && compare(left.right, right.right);
    }
}

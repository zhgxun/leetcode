package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 面试题 04.06. 后继者
 * <p>
 * https://leetcode-cn.com/problems/successor-lcci/
 * <p>
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * <p>
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 */
public class InorderSuccessor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        System.out.println(new InorderSuccessor().inorderSuccessor(root, root.left));
    }

    // 该种解法是根据二叉搜索树的性质顺序查找, 应该很好理解但是时间复杂度高
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // p 存在右子树则下一个中序遍历的节点为最左节点
        if (p.right != null) {
            // 右子树中找最左子节点
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // p 不存在右子树, 则需要在左子树中找它自己的根节点
        TreeNode node = root;
        TreeNode res = null;
        while (p != node) {
            // 说明当前节点在p的父节点中, 在左子树上找, 否则在右子树上找
            if (p.val < node.val) {
                res = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return res;
    }

    boolean isFind = false;
    TreeNode result = null;

    // 时间复杂度满足要求
    // 借助查找标记判断已经找到p节点, 只需要返回它的下一个节点即可
    public TreeNode inorderSuccessorV2(TreeNode root, TreeNode p) {
        if (root == null || result != null) {
            return result;
        }

        inorderSuccessor(root.left, p);

        // 注意这个边界的确定, 返回的中序的后一个元素
        if (root == p) {
            isFind = true;
        } else if (isFind) {
            result = root;
            isFind = false;
        }
        inorderSuccessor(root.right, p);

        return result;
    }
}

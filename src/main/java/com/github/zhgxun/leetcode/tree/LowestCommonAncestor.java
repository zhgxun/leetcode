package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 面试题 04.08. 首个共同祖先
 * <p>
 * https://leetcode-cn.com/problems/first-common-ancestor-lcci/
 * <p>
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
 */
public class LowestCommonAncestor {

    // 递归解法, 比较简洁不过理解起来有点难度
    // 简单的解法是中序遍历, 保存两份数据, 寻找公共前缀即可
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 假设我们从跟结点开始, 采用 DFS 向下遍历
        // 如果当前结点到达叶子结点下的空结点时, 返回空
        // 如果当前结点为 p 或 q 时, 返回当前结点
        if (root == null || root == p || root == q) {
            return root;
        }

        // 这样, 当我们令 left = self.lowestCommonAncestor(root.left, p, q) 时
        // 如果在左子树中找到了 p 或 q, left 会等于 p 或 q, 同理, right 也是一样
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 然后我们进行判断
        // 如果 left 和 right 都不为空, 则为情况1即左右子树中找到根为最近公共祖先
        if (left != null && right != null) {
            return root;
        }
        // 如果 left 和 right 中只有一个不为空, 说明这两个结点在子树中, 则根节点到达子树再进行寻找
        return left != null ? left : right;
    }
}

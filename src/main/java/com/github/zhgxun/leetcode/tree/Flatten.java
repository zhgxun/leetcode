package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 114. 二叉树展开为链表
 * <p>
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * <p>
 * 给定一个二叉树，原地将它展开为链表。
 */
public class Flatten {

    // 可以看到是一种先序遍历方式, 不过注意拼接方式
    // 不是每个节点的右子树拼接
    // 而是将右子树直接拼接到左子树的左右节点, 更新根节点
    // 依次迭代即可
    public void flattenV2(TreeNode root) {
        // 不关心是否返回树的根节点
        while (root != null) {
            // 左子树本身为空则不关心左子树, 检查右子树
            if (root.left == null) {
                root = root.right;
            } else {
                // 说明存在左子树, 找到左子树的最右边节点
                TreeNode temp = root.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                // 将原来的右子树拼接到左子树左右边的节点的下一个值
                temp.right = root.right;
                // 将左子树移动到右子树位置
                root.right = root.left;
                // 清空原来的左子树
                root.left = null;

                root = root.right;
            }
        }
    }

    TreeNode preNode = null;

    // 表面上是前序遍历
    // 实际上前序遍历解决该问题基本无法做到, 因为原本的右节点丢失了
    // 但是后续遍历就弥补了这个额问题
    // 递归始终是最简洁的, 不过就是递归函数的形成比较难以想通
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // 后续遍历是逆向先处理右节点
        flatten(root.right);
        flatten(root.left);
        root.right = preNode;
        root.left = null;
        preNode = root;
    }
}

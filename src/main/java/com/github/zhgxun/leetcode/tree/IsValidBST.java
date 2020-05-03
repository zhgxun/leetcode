package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

/**
 * 面试题 04.05. 合法二叉搜索树
 * <p>
 * https://leetcode-cn.com/problems/legal-binary-search-tree-lcci/
 * <p>
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 * <p>
 * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 它的左、右子树也分别为二叉排序树。
 */
public class IsValidBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);

        System.out.println(new IsValidBST().isValidBST(root));
    }

    // 借助中序遍历为递增数组的解法因为已经遍历过故时间复杂度较高
    // 遍历时提前结束避免最后在进行比较
    // 中序遍历的思路直接使用时间复杂度很高
    // 因此要跳过中序遍历的想法, 看能否在完成中序遍历的过程中就解决
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 处理每一层是否是一颗合法的二叉搜索树
        TreeNode maxLeft = root.left, minRight = root.right;
        // 搜索左子树中的最大值, 在左子树的右边节点中
        while (maxLeft != null && maxLeft.right != null) {
            maxLeft = maxLeft.right;
        }
        // 搜索右子树中的最小值, 在右子树的左节点中
        while (minRight != null && minRight.left != null) {
            minRight = minRight.left;
        }
        // 查看当前层是否满足合法的二叉搜索树
        // 即满足左子树的值均小于根的值, 右子树的值均大于根的值
        boolean result = (maxLeft == null || maxLeft.val < root.val) && (minRight == null || minRight.val > root.val);

        // 递归判断所有层
        return result && isValidBST(root.left) && isValidBST(root.right);
    }
}

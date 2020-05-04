package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;
import com.github.zhgxun.leetcode.TreeNodeUtil;

/**
 * 108. 将有序数组转换为二叉搜索树
 * <p>
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * <p>
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 下面是一些关于 BST 的知识。
 * 中序遍历不能唯一确定一棵二叉搜索树。
 * 先序和后序遍历不能唯一确定一棵二叉搜索树。
 * 先序/后序遍历和中序遍历的关系：
 * inorder = sorted(postorder) = sorted(preorder)，
 * 中序+后序、中序+先序可以唯一确定一棵二叉树。
 */
public class SortedArrayToBST {

    public static void main(String[] args) {
        TreeNode root = new SortedArrayToBST().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println(TreeNodeUtil.preOrderTraversal(root));
        System.out.println(TreeNodeUtil.inOrderTraversal(root));
        System.out.println(TreeNodeUtil.postOrderTraversal(root));
    }

    private int[] nums;

    // 每次折半构造一棵树即可
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return helper(0, nums.length - 1);
    }

    public TreeNode helper(int start, int end) {
        if (start > end) {
            return null;
        }

        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = helper(start, middle - 1);
        root.right = helper(middle + 1, end);

        return root;
    }
}

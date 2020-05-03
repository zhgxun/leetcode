package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 530. 二叉搜索树的最小绝对差
 * <p>
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 * <p>
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 */
public class GetMinimumDifference {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);

        System.out.println((new GetMinimumDifference()).getMinimumDifference(root));
    }

    private List<Integer> list = new ArrayList<>();

    // 借助中序遍历的方式时间复杂度很高
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        int size = list.size();
        int minVal = Integer.MAX_VALUE;
        for (int i = 1; i < size; i++) {
            int diff = list.get(i) - list.get(i - 1);
            if (diff < minVal) {
                minVal = diff;
            }
        }

        return size == 1 ? list.get(0) : minVal;
    }

    void inOrder(TreeNode root) {
        if (root.left != null) {
            inOrder(root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            inOrder(root.right);
        }
    }

    private int preVal = -1;
    private int minVal = Integer.MAX_VALUE;

    // 借助中序遍历但是不做额外保存申请
    // 优化后的中序遍历比以往的效率提升了一倍多
    public int getMinimumDifferenceV2(TreeNode root) {
        inOrderV2(root);
        return minVal;
    }

    void inOrderV2(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            inOrderV2(root.left);
        }
        if (preVal != -1) {
            minVal = Math.min(minVal, root.val - preVal);
        }
        preVal = root.val;
        if (root.right != null) {
            inOrderV2(root.right);
        }
    }
}

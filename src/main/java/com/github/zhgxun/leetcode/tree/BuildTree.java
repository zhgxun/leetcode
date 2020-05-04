package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 */
public class BuildTree {

    public static void main(String[] args) {
        System.out.println(new BuildTree().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }

    // 前序遍历的首节点是根
    // 中序遍历到前序遍历的节点部分是左树
    private int preIndex = 0;
    private int[] preOrder;
    Map<Integer, Integer> map = new HashMap<>();

    // 前序遍历是用来找根节点的
    // 中序遍历是用来找左右子树的
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preOrder = preorder;
        // 中序遍历存于map中
        int index = 0;
        for (Integer in : inorder) {
            map.put(in, index++);
        }

        return helper(0, inorder.length);
    }

    // 按中序遍历的下标从下到上构造树
    public TreeNode helper(int inLeft, int inRight) {
        if (inLeft == inRight) {
            return null;
        }

        // 前序的头即为当前树的根
        int rootVal = preOrder[preIndex];
        TreeNode root = new TreeNode(rootVal);

        // 根的值将中序分割为左右子树两部分
        int split = map.get(rootVal);

        preIndex++;

        // 构建当前根的左子树
        root.left = helper(inLeft, split);

        // 构建当前根的右子树
        root.right = helper(split + 1, inRight);

        // 返回树
        return root;
    }
}

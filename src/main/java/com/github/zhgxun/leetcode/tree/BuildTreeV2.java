package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;
import com.github.zhgxun.leetcode.TreeNodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * <p>
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 */
public class BuildTreeV2 {

    public static void main(String[] args) {
        TreeNode root = new BuildTreeV2().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println(TreeNodeUtil.preOrderTraversal(root));
        System.out.println(TreeNodeUtil.inOrderTraversal(root));
        System.out.println(TreeNodeUtil.postOrderTraversal(root));
    }

    int[] postOrder;
    int preIndex;
    Map<Integer, Integer> map = new HashMap<>();

    // 中序遍历找左右子树
    // 后续遍历逆向查找根节点
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postOrder = postorder;
        preIndex = postorder.length - 1;
        int index = 0;
        for (Integer in : inorder) {
            map.put(in, index++);
        }

        return helper(0, inorder.length);
    }

    public TreeNode helper(int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }

        int rootVale = postOrder[preIndex];
        TreeNode root = new TreeNode(rootVale);
        preIndex--;
        int index = map.get(rootVale);
        root.right = helper(index + 1, inRight);
        root.left = helper(inLeft, index);

        return root;
    }
}

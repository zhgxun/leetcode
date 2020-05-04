package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * <p>
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 * <p>
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 */
public class GenerateTrees {

    public static void main(String[] args) {
        List<TreeNode> treeNodes = new GenerateTrees().generateTrees(3);
    }

    // 我们从序列 1 ..n 中取出数字 i，作为当前树的树根。于是，剩余 i - 1 个元素可用于左子树，n - i 个元素用于右子树。
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        return generate(1, n);
    }

    public LinkedList<TreeNode> generate(int start, int end) {
        LinkedList<TreeNode> treeList = new LinkedList<>();
        if (start > end) {
            treeList.add(null);
            return treeList;
        }

        for (int i = start; i <= end; i++) {
            LinkedList<TreeNode> leftTrees = generate(start, i - 1);
            LinkedList<TreeNode> rightTrees = generate(i + 1, end);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode tree = new TreeNode(i);
                    tree.left = left;
                    tree.right = right;
                    treeList.add(tree);
                }
            }
        }

        return treeList;
    }
}

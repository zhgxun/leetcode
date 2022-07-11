package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 */
public class EasyFindMode {

    public static void main(String[] args) {
        /*
         * 样例树
         *          5
         *         /\
         *        3  6
         *       / \   \
         *      2   3   6
         */
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(3);
        tree.left.left = new TreeNode(2);
        tree.left.right = new TreeNode(3);
        tree.right = new TreeNode(6);
        tree.right.right = new TreeNode(6);

        EasyFindMode object = new EasyFindMode();
        System.out.println(Arrays.toString(object.findMode(tree)));
    }

    /**
     * 每次更新的方式可以优化掉一个 map 计数结构
     */
    private final List<Integer> result = new ArrayList<>();
    private int base, count, maxCount;

    /**
     * 该种做法是 O(n) 的空间复杂度, 排除递归栈本身的空间占用
     * 如果要 O(1) 的空间复杂度, 需要使用迭代并且替换树方可
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[]{};
        dfs(root);
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root.left);
        update(root.val);
        dfs(root.right);
    }

    private void update(int x) {
        if (x == base) {
            count++;
        } else {
            count = 1;
            base = x;
        }

        if (count == maxCount) {
            result.add(x);
        } else if (count > maxCount) {
            result.clear();
            result.add(x);
            maxCount = count;
        }
    }
}

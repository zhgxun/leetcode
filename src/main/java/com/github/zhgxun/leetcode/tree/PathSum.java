package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和 II
 * <p>
 * https://leetcode-cn.com/problems/path-sum-ii/
 * <p>
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 */
public class PathSum {

    List<List<Integer>> resultList = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return resultList;
        }
        LinkedList<Integer> tempList = new LinkedList<>();
        findSum(root, sum, tempList);
        return resultList;
    }

    public void findSum(TreeNode root, int sum, LinkedList<Integer> tempList) {
        if (root == null) {
            return;
        }
        tempList.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            resultList.add(new ArrayList<>(tempList));
        }

        findSum(root.left, sum - root.val, tempList);
        findSum(root.right, sum - root.val, tempList);
        // 说明一个节点处理完毕移除这个元素, 不管是否满足条件都移除继续后面的节点处理
        tempList.removeLast();
    }
}

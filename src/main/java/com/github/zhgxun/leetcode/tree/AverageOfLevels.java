package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 637. 二叉树的层平均值
 * <p>
 * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
 * <p>
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组
 */
public class AverageOfLevels {

    private final List<List<Double>> levels = new ArrayList<>();

    // 典型的二叉树的层次遍历
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        order(root, 0);
        List<Double> resultList = new ArrayList<>();
        for (List<Double> level : levels) {
            resultList.add(average(level));
        }

        return resultList;
    }

    public void order(TreeNode root, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add((double) root.val);
        if (root.left != null) {
            order(root.left, level + 1);
        }
        if (root.right != null) {
            order(root.right, level + 1);
        }
    }

    public Double average(List<Double> list) {
        Double sum = 0d;
        for (Double d : list) {
            sum += d;
        }
        return sum / list.size();
    }
}

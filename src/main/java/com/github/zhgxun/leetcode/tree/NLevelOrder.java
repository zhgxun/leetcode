package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 429. N叉树的层序遍历
 * <p>
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * <p>
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 */
public class NLevelOrder {

    List<List<Integer>> levels = new ArrayList<>();

    // 递归要更简单
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return levels;
        }
        helper(root, 0);
        return levels;
    }

    public void helper(Node root, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(root.val);
        if (root.children != null) {
            for (Node children : root.children) {
                helper(children, level + 1);
            }
        }
    }
}

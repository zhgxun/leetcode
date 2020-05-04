package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 589. N叉树的前序遍历
 * <p>
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * <p>
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 */
public class NPreOrder {

    // 迭代法遍历
    // 效率不如递归执行的好
    public List<Integer> preOrderV2(Node root) {
        List<Integer> resultList = new ArrayList<>();
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            Node node = linkedList.pollLast();
            if (node == null) {
                continue;
            }

            resultList.add(node.val);
            if (node.children != null) {
                // 注意要反向入队
                int size = node.children.size();
                for (int i = size - 1; i >= 0; i--) {
                    linkedList.add(node.children.get(i));
                }
            }
        }

        return resultList;
    }

    // 递归很容易理解
    public List<Integer> preOrder(Node root) {
        List<Integer> resultList = new ArrayList<>();
        order(root, resultList);
        return resultList;
    }

    public void order(Node root, List<Integer> resultList) {
        if (root == null) {
            return;
        }
        resultList.add(root.val);
        for (Node node : root.children) {
            order(node, resultList);
        }
    }
}

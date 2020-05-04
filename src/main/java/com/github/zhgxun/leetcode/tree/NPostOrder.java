package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 590. N叉树的后序遍历
 * <p>
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * <p>
 * 590. N叉树的后序遍历
 */
public class NPostOrder {

    // 迭代效率不如递归
    public List<Integer> postorderV2(Node root) {
        LinkedList<Integer> resultList = new LinkedList<>();
        LinkedList<Node> linkedList = new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            Node node = linkedList.pollLast();
            if (node == null) {
                continue;
            }
            if (node.children != null) {
                linkedList.addAll(node.children);
            }
            resultList.addFirst(node.val);
        }

        return resultList;
    }

    public List<Integer> postorder(Node root) {
        List<Integer> resultList = new ArrayList<>();
        order(root, resultList);
        return resultList;
    }

    public void order(Node root, List<Integer> resultList) {
        if (root == null) {
            return;
        }
        if (root.children != null) {
            for (Node node : root.children) {
                order(node, resultList);
            }
        }
        resultList.add(root.val);
    }
}

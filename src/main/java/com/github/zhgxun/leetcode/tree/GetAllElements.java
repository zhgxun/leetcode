package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1305. 两棵二叉搜索树中的所有元素
 * <p>
 * https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/
 * <p>
 * 给你 root1 和 root2 这两棵二叉搜索树。
 * <p>
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。
 */
public class GetAllElements {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(3);

        System.out.println(new GetAllElements().getAllElements(root1, root2));
    }

    // 中序遍历加排序
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return new ArrayList<>();
        }

        // 中序遍历树
        List<Integer> aList = new ArrayList<>();
        inOrder(root1, aList);

        List<Integer> bList = new ArrayList<>();
        inOrder(root2, bList);

        // 合并2个有序排序数组为排序数组
        return merge(aList, bList);
    }

    // 获取树的中序遍历结果
    public void inOrder(TreeNode root, List<Integer> resultList) {
        if (root == null) {
            return;
        }
        inOrder(root.left, resultList);
        resultList.add(root.val);
        inOrder(root.right, resultList);
    }

    // 合并两个有序数组至一个新有序数组中
    public List<Integer> merge(List<Integer> a, List<Integer> b) {
        int aLen = a.size();
        if (aLen == 0) {
            return b;
        }
        int bLen = b.size();
        if (bLen == 0) {
            return a;
        }

        List<Integer> target = new ArrayList<>(aLen + bLen);
        int i = 0, j = 0;
        while (i < aLen && j < bLen) {
            if (a.get(i) <= b.get(j)) {
                target.add(a.get(i));
                i++;
            } else {
                target.add(b.get(j));
                j++;
            }
        }

        while (i < aLen) {
            target.add(a.get(i));
            i++;
        }

        while (j < bLen) {
            target.add(b.get(j));
            j++;
        }

        return target;
    }
}

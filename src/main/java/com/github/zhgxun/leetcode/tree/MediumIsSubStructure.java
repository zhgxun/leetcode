package com.github.zhgxun.leetcode.tree;

import com.github.zhgxun.leetcode.TreeNode;

// https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/
public class MediumIsSubStructure {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return dfsIsSubStructure(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean dfsIsSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }

        if (A == null || A.val != B.val) {
            return false;
        }

        return dfsIsSubStructure(A.left, B.left) && dfsIsSubStructure(A.right, B.right);
    }
}

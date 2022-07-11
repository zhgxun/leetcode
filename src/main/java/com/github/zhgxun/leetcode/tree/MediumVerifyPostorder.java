package com.github.zhgxun.leetcode.tree;

import java.util.Queue;

// https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
public class MediumVerifyPostorder {

    public static void main(String[] args) {
        MediumVerifyPostorder object = new MediumVerifyPostorder();
        System.out.println(object.verifyPostorder(new int[]{4, 8, 6, 12, 16, 14, 10}));
    }

    public boolean verifyPostorder(int[] postorder) {
        return dfsVerifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean dfsVerifyPostorder(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }

        // 后续遍历满足 根的值 大于所有的左子树  根的值小于所有的右子树
        // postorder[right] 即为当前段的根节点
        int p = left;
        while (postorder[p] < postorder[right]) p++;
        // 此时 p 的位置为左右子树的分界位置
        int m = p;
        // 右子树走到最后根的位置
        while (postorder[p] > postorder[right]) p++;

        return p == right && dfsVerifyPostorder(postorder, left, m - 1) && dfsVerifyPostorder(postorder, m, right - 1);
    }
}

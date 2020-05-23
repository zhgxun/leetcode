package com.github.zhgxun.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simple {

    public static void main(String[] args) {
        new Simple().myPow(2, 10);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        for (Integer i : map.values()) {

        }
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int len = 0;
        int num = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len) {
                len = entry.getValue();
                num = entry.getKey();
            }
        }
        return num;
    }

    public double myPow(double x, int n) {
        double res = 1;
        boolean flag = false;
        if (n < 0) {
            n = -n;
            flag = true;
        }


        return flag ? 1 / res : res;
    }

    public boolean isPowerOfTwo(int n) {
        if (n == 1) {
            return true;
        }
        int result = 1;
        for (; ; ) {
            result *= 2;
            System.out.println(result);
            if (result == n) {
                return true;
            } else if (result > n) {
                return false;
            }
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        helper(result, 0, root);
        return result;
    }

    public void helper(List<List<Integer>> result, int level, TreeNode root) {
        if (result.size() == level) {
            result.add(level, new ArrayList<>());
        }
        result.get(level).add(root.val);
        if (root.left != null) {
            helper(result, level + 1, root.left);
        }
        if (root.right != null) {
            helper(result, level + 1, root.right);
        }
    }
}

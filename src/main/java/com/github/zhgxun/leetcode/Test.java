package com.github.zhgxun.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 4};
        new Test().quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private final Map<Integer, Boolean> map = new HashMap<>();

    public int nthUglyNumber(int n) {
        if (n == 1) return 1;
        map.put(0, false);
        map.put(1, true);
        int count = 0;
        for (int i = 1; ; i++) {
            if (isUgly(i)) {
                count++;
                if (count == n) return i;
            }
        }
    }

    private boolean isUgly(int n) {
        if (map.containsKey(n)) return map.get(n);
        boolean result = false;
        if (n % 2 == 0) {
            result = isUgly(n / 2);
        } else if (n % 3 == 0) {
            result = isUgly(n / 3);
        } else if (n % 5 == 0) {
            result = isUgly(n / 5);
        }
        map.put(n, result);
        return result;
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int index = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < nums[right]) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index++] = temp;
            }
        }

        int temp = nums[right];
        nums[right] = nums[index];
        nums[index] = temp;

        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }

    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int middle = (left + right) / 2;
        mergeSort(nums, left, middle);
        mergeSort(nums, middle + 1, right);
        merge(nums, left, middle, right);
    }

    public void merge(int[] nums, int left, int middle, int right) {
        int[] temp = new int[right - left + 1];
        int index = 0, i = left, j = middle + 1;
        while (i <= middle && j <= right) {
            temp[index++] = nums[i] >= nums[j] ? nums[j++] : nums[i++];
        }
        while (i <= middle) temp[index++] = nums[i++];
        while (j <= right) temp[index++] = nums[j++];
        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int left, int right) {
        List<TreeNode> nodes = new LinkedList<>();
        if (left > right) {
            nodes.add(null);
            return nodes;
        }

        for (int i = left; i <= right; i++) {
            List<TreeNode> leftNodes = dfs(left, i - 1);
            List<TreeNode> rightNodes = dfs(i + 1, right);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    node.right = rightNode;
                    nodes.add(node);
                }
            }
        }
        return nodes;
    }
}

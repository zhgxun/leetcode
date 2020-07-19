package com.github.zhgxun.leetcode.string;

import com.github.zhgxun.leetcode.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 567. 字符串的排列
 * <p>
 * https://leetcode-cn.com/problems/permutation-in-string/
 */
public class CheckInclusion {

    public static void main(String[] args) {
        System.out.println(new CheckInclusion().numSquares(20));
        System.out.println(new CheckInclusion().numSquares(100));
    }

    int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);

        for (int i = 2; i < n; i++) // 从2开始枚举
            if (isPrim[i]) // 倍数均不是素数
                for (int j = 2 * i; j < n; j += i) isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++) if (isPrim[i]) count++;
        return count;
    }

    public int numSquares(int n) {
        // 初始化dp数组
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 填充可能有效的解
        int index = (int) Math.sqrt(n) + 1;
        int[] max = new int[index];
        for (int i = 1; i < index; ++i) {
            max[i] = i * i;
        }

        for (int i = 1; i <= n; i++) { // 迭代所有的整数
            for (int j = 1; j < index; j++) { // 在可能的预选列表中查找
                if (i < max[j]) break;
                dp[i] = Math.min(dp[i], dp[i - max[j]] + 1);
            }
        }
        return dp[n];
    }

    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return result;

        dfs(nums, 0, 0, new ArrayList<>());
        return result;
    }

    private void dfs(int[] nums, int level, int index, List<Integer> res) {
        if (level == nums.length) {
            result.add(new ArrayList<>(res));
            return;
        }

        dfs(nums, level + 1, index + 1, res);
        res.add(nums[index]);
        dfs(nums, level + 1, index + 1, res);
        res.remove(res.size() - 1);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 快慢指针定位链表中点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 对两端链表进行排序
        ListNode temp = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(temp);

        // 合并两端有序链表
        ListNode node = new ListNode(0);
        ListNode nodeHead = node;
        while (left != null && right != null) {
            if (left.val >= right.val) {
                node.next = right;
                right = right.next;
            } else {
                node.next = left;
                left = left.next;
            }
            node = node.next;
        }
        if (left != null) node.next = left;
        if (right != null) node.next = right;

        return nodeHead.next;
    }

    // 理解为异位词
    public boolean checkInclusion(String s1, String s2) {
        char[] sCharList = s1.toCharArray();
        int[] needs = new int[26];
        for (char ch : sCharList) {
            needs[ch - 'a']++;
        }

        int targetLen = 0;
        for (int i = 0; i < 26; i++) {
            if (needs[i] > 0) targetLen++;
        }

        int[] windows = new int[26];
        int haveLen = 0;
        char[] pCharList = s2.toCharArray();
        for (int left = 0, right = 0; right < pCharList.length; right++) {
            int num = pCharList[right] - 'a';
            windows[num]++;
            if (windows[num] == needs[num]) haveLen++;

            while (right - left + 1 == sCharList.length) {
                if (targetLen == haveLen) return true;
                int leftNum = pCharList[left] - 'a';
                if (windows[leftNum] == needs[leftNum]) haveLen--;
                windows[leftNum]--;
                left++;
            }
        }

        return false;
    }
}

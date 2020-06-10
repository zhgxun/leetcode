package com.github.zhgxun.leetcode.string;

/**
 * 76. 最小覆盖子串
 * <p>
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class MinWindow {

    public static void main(String[] args) {
        System.out.println(new MinWindow().minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        char[] tCharList = t.toCharArray();
        int[] needs = new int[128];
        for (char ch : tCharList) {
            needs[ch - 'A']++;
        }

        int targetLen = 0;
        for (int i = 0; i < 128; i++) {
            if (needs[i] > 0) targetLen++;
        }

        // 记录符合条件的开始坐标和最小长度
        int start = 0;
        int minLen = Integer.MAX_VALUE;

        int haveLen = 0;
        int[] windows = new int[128];
        char[] sCharList = s.toCharArray();
        for (int left = 0, right = 0; right < sCharList.length; right++) {
            int num = sCharList[right] - 'A';
            windows[num]++;
            if (needs[num] == windows[num]) haveLen++;

            // 找到了相同数目的字符, 此时看滑动窗口的最小值是否满足要求
            while (targetLen == haveLen) {
                // 更新最小坐标和长度的条件
                if (right - left + 1 < minLen) {
                    start = left;
                    minLen = right - left + 1;
                }

                int leftNum = sCharList[left] - 'A';
                if (needs[leftNum] == windows[leftNum]) haveLen--;
                windows[leftNum]--;
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}

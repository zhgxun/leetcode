package com.github.zhgxun.leetcode.string;

/**
 * 5. 最长回文子串
 * <p>
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 */
public class LongestPalindrome {

    public static void main(String[] args) {
    }

    // 奇数和偶数 存在 2n - 1 个中心
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int oddLen = findLen(s, i, i);
            int evenLen = findLen(s, i, i + 1);
            int maxLen = Math.max(oddLen, evenLen);
            if (maxLen > end - start) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int findLen(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 此时字符串不相等, 故相同的字符串长度减1
        return right - left - 1;
    }
}

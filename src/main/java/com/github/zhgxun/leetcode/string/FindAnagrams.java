package com.github.zhgxun.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * <p>
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 * <p>
 * https://www.bilibili.com/video/BV1WC4y1a7hq?from=search&seid=6464771871012239601
 */
public class FindAnagrams {

    public static void main(String[] args) {
        System.out.println(new FindAnagrams().findAnagrams("cbaebabacd", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        // 记录目标字符的计数
        char[] pCharList = p.toCharArray();
        int[] needs = new int[26];
        for (char ch : pCharList) {
            needs[ch - 'a']++;
        }
        // 目标单词的数量
        int targetLen = 0;
        for (int i = 0; i < 26; i++) {
            if (needs[i] > 0) {
                targetLen++;
            }
        }

        // 遍历原始字符串
        char[] sCharList = s.toCharArray();
        int haveLen = 0;
        int[] windows = new int[26];
        for (int left = 0, right = 0; right < sCharList.length; right++) {
            // 保存原始字符
            int num = sCharList[right] - 'a';
            windows[num]++;
            // 如果找到相等的字符则记录有效字符数量
            if (windows[num] == needs[num]) haveLen++;

            // 滑动窗口字符长度跟目标字符长度相同则需要处理窗口
            while (right - left + 1 == pCharList.length) {
                // 此时该窗口是否为有效的答案
                if (haveLen == targetLen) result.add(left);
                int leftNum = sCharList[left] - 'a';
                if (windows[leftNum] == needs[leftNum]) haveLen--;
                windows[leftNum]--;
                left++;
            }
        }

        return result;
    }
}

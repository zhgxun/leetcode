package com.github.zhgxun.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 30. 串联所有单词的子串
 * <p>
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
 */
public class FindSubstring {

    public static void main(String[] args) {
        System.out.println(new FindSubstring().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(new FindSubstring().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int[] needs = new int[26];
        int wordLen = 0;
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                wordLen++;
                needs[ch - 'a']++;
            }
        }

        int targetLen = 0;
        for (int i = 0; i < 26; i++) {
            if (needs[i] > 0) targetLen++;
        }

        int haveLen = 0;
        int[] windows = new int[26];
        char[] chars = s.toCharArray();
        for (int left = 0, right = 0; right < chars.length; right++) {
            int num = chars[right] - 'a';
            windows[num]++;
            if (windows[num] == needs[num]) haveLen++;

            while (right - left + 1 == wordLen) {
                if (haveLen == targetLen) result.add(left);
                int leftNum = chars[left] - 'a';
                if (windows[leftNum] == needs[leftNum]) haveLen--;
                windows[leftNum]--;
                left++;
            }
        }

        return result;
    }
}

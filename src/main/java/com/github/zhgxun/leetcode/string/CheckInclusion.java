package com.github.zhgxun.leetcode.string;

/**
 * 567. 字符串的排列
 * <p>
 * https://leetcode-cn.com/problems/permutation-in-string/
 */
public class CheckInclusion {

    public static void main(String[] args) {
        System.out.println(new CheckInclusion().checkInclusion("abc", "ccccbbbbaaaa"));
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

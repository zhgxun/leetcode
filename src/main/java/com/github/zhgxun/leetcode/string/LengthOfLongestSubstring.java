package com.github.zhgxun.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * <p>
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, left = 0, right = 0;
        while (left < n && right < n) {
            // 这一步比较难以理解, 果然算法都是4分理解, 3分背诵, 3分练习
            // 如果剩下的字符中重复比较分散, 会一直执行 remove 操作直到剩下的元素不重复位置
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
                // 每次都统计最大长度
                ans = Math.max(ans, right - left);
            } else {
                set.remove(s.charAt(left++));
            }
        }
        return ans;
    }
}

package com.github.zhgxun.leetcode.string;

public class ReverseStr {

    public static void main(String[] args) {
        System.out.println(new ReverseStr().reverseStr("abcdefg", 2));
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int start = 0; start < chars.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, chars.length - 1);
            while (i < j) {
                char tmp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = tmp;
            }
        }
        return new String(chars);
    }
}

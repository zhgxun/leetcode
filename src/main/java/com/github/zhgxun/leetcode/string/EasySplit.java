package com.github.zhgxun.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 实现一个类似 split explode 的函数
 * 其实这个也挺不容易的
 */
public class EasySplit {

    public static void main(String[] args) {
        EasySplit object = new EasySplit();
        String str = "aaabbbcccaabaaabbbcccaabaaabbbcccaabaaabbbcccaabaaabbbcccaabaaabbbcccaab";
        String sep = "aab";
        System.out.println(Arrays.toString(str.split(sep)));
        System.out.println(object.split(str, sep));
        System.out.println(object.splitForApi(str, sep));
    }

    private List<String> split(String str, String sep) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ) {
            // 分隔符的首字符匹配才可能是目标分隔符
            if (str.charAt(i) != sep.charAt(0)) {
                sb.append(str.charAt(i));
                i++;
                continue;
            }

            // 对比接下来的子字符串和分隔符是否完全一致
            int start = i;
            int j = 0;
            while (start < str.length() && j < sep.length() && str.charAt(start) == sep.charAt(j)) {
                start++;
                j++;
            }
            // 相等则说明当前满足分隔符, 否则当前字符串不能别丢弃
            // 比如 目标字符串是 aaabbbcccaaabbbcccaaabbbccc 分隔符是 aab
            // 第一个 a 不能被丢弃
            if (j == sep.length()) {
                result.add(sb.toString());
                i += j;
                sb = new StringBuilder();
            } else {
                sb.append(str.charAt(i));
                i++;
            }
        }

        // 考虑最后一个子串
        if (sb.length() > 0) result.add(sb.toString());

        return result;
    }

    // 暴力法
    // substring 新建一个字符串返回, 保存目标字符串也是新建一个字符串返回
    // 自己处理的话可以把 原始字符串的新建这个过程优化掉
    private List<String> splitForApi(String str, String sep) {
        List<String> result = new ArrayList<>();
        int left = 0;
        for (int i = 0; i < str.length(); ) {
            if (str.charAt(i) == sep.charAt(0) && i + sep.length() <= str.length()) {
                String sub = str.substring(i, i + sep.length());
                if (sub.equals(sep)) {
                    result.add(str.substring(left, i));
                    i += sep.length();
                    left = i;
                    continue;
                }
            }
            i++;
        }
        if (left < str.length()) {
            result.add(str.substring(left));
        }

        return result;
    }
}

package com.github.zhgxun.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现 {@link String#split(String)} 字符串分割函数
 */
public class Split {

    public static void main(String[] args) {
        System.out.println(new Split().split("abc", "b"));
        System.out.println(new Split().split("abc", "ab"));
        System.out.println(new Split().split("abc", "abc"));
        System.out.println(new Split().split("abcdabcdeabc", "bc"));
        System.out.println(new Split().split("a$bb$ccc$", "$$"));
    }

    private List<String> split(String source, String dlt) {
        List<String> result = new ArrayList<>();
        // 记录上一个有效分隔符位置
        int i = 0, start = 0, end;
        while (i < source.length()) {
            // 可能找到分隔符开始位置
            if (source.charAt(i) == dlt.charAt(0)) {
                end = i;
                // 继续比较两个字符串看是否完全匹配
                int j = 0;
                while (i < source.length() && j < dlt.length() && source.charAt(i) == dlt.charAt(j)) {
                    i++;
                    j++;
                }
                // 如果 dlt 刚好到末尾则找到一个分割前的字符串
                if (j == dlt.length()) {
                    if (start < end) { // 空字符串本身不需记录
                        result.add(source.substring(start, end));
                    }
                    start = i;
                }
            } else {
                i++;
            }
        }

        // 最后的字符串
        if (start < source.length()) {
            result.add(source.substring(start));
        }

        return result;
    }
}

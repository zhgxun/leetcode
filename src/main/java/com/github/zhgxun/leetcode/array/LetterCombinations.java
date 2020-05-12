package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * <p>
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinations {

    public static void main(String[] args) {
        System.out.println(new LetterCombinations().letterCombinations("2"));
    }

    public static Map<String, String> digitsMap = new HashMap<>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> result = new ArrayList<>();

    public void generate(String prev, String digits) {
        if (digits.length() == 0) {
            result.add(prev);
            return;
        }

        String key = digits.substring(0, 1);
        String letters = digitsMap.get(key);
        int length = letters.length();
        for (int i = 0; i < length; i++) {
            String letter = digitsMap.get(key).substring(i, i + 1);
            generate(prev + letter, digits.substring(1));
        }
    }

    // 回溯法
    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0) {
            generate("", digits);
        }
        return result;
    }
}

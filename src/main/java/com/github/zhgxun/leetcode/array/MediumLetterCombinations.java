package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
public class MediumLetterCombinations {

    private final List<String> result = new ArrayList<>();
    private final Map<Integer, String> map = new HashMap<>() {{
        put(2, "abc");
        put(3, "def");
        put(4, "ghi");
        put(5, "jkl");
        put(6, "mno");
        put(7, "pqrs");
        put(8, "tuv");
        put(9, "wxyz");
    }};

    public static void main(String[] args) {
        MediumLetterCombinations object = new MediumLetterCombinations();
        System.out.println(object.letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        dfsLetterCombinations(digits, 0, new StringBuilder());
        return result;
    }

    private void dfsLetterCombinations(String digits, int index, StringBuilder sb) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String s = map.get(digits.charAt(index) - '0');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            dfsLetterCombinations(digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

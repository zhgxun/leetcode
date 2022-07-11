package com.github.zhgxun.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
public class MediumPermutation {

    public static void main(String[] args) {
        MediumPermutation object = new MediumPermutation();
        System.out.println(Arrays.toString(object.permutation("abc")));
    }

    private final List<String> result = new ArrayList<>();

    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        StringBuilder sb = new StringBuilder();
        dfsPermutation(chars, new boolean[s.length()], sb);

        String[] res = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    private void dfsPermutation(char[] chars, boolean[] visited, StringBuilder sb) {
        if (sb.length() >= chars.length) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (visited[i] || (i > 0 && visited[i - 1] && chars[i] == chars[i - 1])) {
                continue;
            }

            char c = chars[i];

            visited[i] = true;
            sb.append(c);

            dfsPermutation(chars, visited, sb);

            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

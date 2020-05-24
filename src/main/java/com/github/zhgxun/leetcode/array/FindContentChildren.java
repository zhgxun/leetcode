package com.github.zhgxun.leetcode.array;

import java.util.Arrays;

/**
 * 455. 分发饼干
 * <p>
 * https://leetcode-cn.com/problems/assign-cookies/
 */
public class FindContentChildren {

    // g 小孩的胃口
    // s 饼干的尺寸
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
                j++;
                count++;
            } else {
                j++;
            }
        }

        return count;
    }
}

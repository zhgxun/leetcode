package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

    public static void main(String[] args) {
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("25525511135"));
        System.out.println(0xFF);
    }

    public List<String> restoreIpAddresses(String s) {
        if (s.length() == 0) {
            return new ArrayList<>();
        }
        List<String> dp = new ArrayList<>();
        dp.add("" + s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            List<String> newDp = new ArrayList<>();
            for (String s1 : dp) {
                String[] oldString = s1.split("\\.");
                int n = oldString.length;
                String s2 = s1 + "." + s.charAt(i);
                if (n <= 3) {
                    newDp.add(s2);
                }
                if (!oldString[n - 1].equals("0")) {
                    if (n <= 4 && Integer.parseInt(oldString[n - 1] + s.charAt(i)) <= 255) {
                        newDp.add(s1 + s.charAt(i));
                    }
                }
            }
            dp = new ArrayList<>(newDp);
        }

        List<String> list = new ArrayList<>();
        for (String s1 : dp) {
            if (s1.split("\\.").length == 4) list.add(s1);
        }
        return list;
    }

    static final int SEG_COUNT = 4;
    private final List<String> ans = new ArrayList<>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddressesV2(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segCount, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segCount == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuilder ipAddr = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segCount] = 0;
            dfs(s, segCount + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 255) {
                segments[segCount] = addr;
                dfs(s, segCount + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }
}

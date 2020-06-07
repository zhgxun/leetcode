package com.github.zhgxun.leetcode.string;

import java.util.HashSet;

/**
 * 433. 最小基因变化
 * <p>
 * https://leetcode-cn.com/problems/minimum-genetic-mutation/
 */
public class MinMutation {

    private int minSum = Integer.MAX_VALUE;

    public int minMutation(String start, String end, String[] bank) {
        dfs(new HashSet<>(), start, end, bank, 1);
        return minSum == Integer.MAX_VALUE ? 0 : minSum;
    }

    public void dfs(HashSet<String> bankSet, String start, String end, String[] bankList, int sum) {
        if (start.equals(end)) {
            minSum = Math.min(minSum, sum);
            return;
        }

        for (String bank : bankList) {
            int diffCount = 0;
            for (int i = 0; i < bank.length(); i++) {
                if (bank.charAt(i) != start.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1) {
                        break;
                    }
                }
            }

            if (diffCount == 1 && !bankSet.contains(bank)) {
                bankSet.add(bank);
                dfs(bankSet, bank, end, bankList, sum + 1);
                bankSet.remove(bank);
            }
        }
    }
}

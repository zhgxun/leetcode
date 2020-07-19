package com.github.zhgxun.leetcode.array;

public class MinCostTickets {

    private int[] days, costs, cache;
    private final int[] duration = new int[]{1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        cache = new int[days.length];
        return dfs(0);
    }

    private int dfs(int day) {
        if (day >= days.length) return 0;
        if (cache[day] != 0) return cache[day];
        int cur = day;
        cache[day] = Integer.MAX_VALUE;
        for (int i = 0; i < duration.length; i++) {
            while (cur < days.length && days[cur] < days[day] + duration[i]) cur++;
            cache[day] = Math.min(cache[day], dfs(cur) + costs[i]);
        }

        return cache[day];
    }
}

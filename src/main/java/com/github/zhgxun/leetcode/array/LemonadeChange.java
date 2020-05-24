package com.github.zhgxun.leetcode.array;

public class LemonadeChange {

    // [5,5,5,10,20]

    public boolean lemonadeChange(int[] bills) {
        if (bills == null || bills.length == 0 || bills[0] != 5) {
            return false;
        }

        int[] coin = new int[2];

        for (int bill : bills) {
            if (bill == 5) {
                coin[0]++;
            } else if (bill == 10) {
                if (coin[0] == 0) {
                    return false;
                }
                coin[0]--;
                coin[1]++;
            } else {
                if (coin[1] >= 1 && coin[0] >= 1) {
                    coin[1]--;
                    coin[0]--;
                } else if (coin[0] >= 3) {
                    coin[0] -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}

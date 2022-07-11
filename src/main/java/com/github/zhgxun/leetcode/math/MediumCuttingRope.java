package com.github.zhgxun.leetcode.math;

// https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
public class MediumCuttingRope {

    public static void main(String[] args) {
        MediumCuttingRope object = new MediumCuttingRope();
        System.out.println(object.cuttingRope(127));
    }

    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int num = n / 3;
        int remain = n % 3;

        if (remain == 0) {
            return (int) pow(num);
        }
        if (remain == 1) {
            return (int) (pow(num - 1) * 4 % 1000000007);
        }
        return (int) (pow(num) * 2 % 1000000007);
    }

    private long pow(int num) {
        long res = 1;
        for (int i = 0; i < num; i++) {
            res = res * 3 % 1000000007;
        }
        return res;
    }
}

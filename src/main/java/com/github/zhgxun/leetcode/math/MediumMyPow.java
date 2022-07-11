package com.github.zhgxun.leetcode.math;

/**
 * 50. Pow(x, n)
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MediumMyPow {

    public static void main(String[] args) {
        MediumMyPow object = new MediumMyPow();
        System.out.println(object.myPow(2.00000, 10));
        System.out.println(object.myPow(2.10000, 3));
        System.out.println(object.myPow(2.00000, -2));
    }

    public double myPow(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        return quickPow(x, n);
    }

    private double quickPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        double half = quickPow(x, n / 2);

        return n % 2 == 0 ? half * half : half * half * x;
    }
}

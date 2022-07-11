package com.github.zhgxun.leetcode.math;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EasyMySqrt {

    public static void main(String[] args) {
        EasyMySqrt object = new EasyMySqrt();
        System.out.println(object.mySqrt(4));
        System.out.println(object.mySqrt(8));
        System.out.println(object.mySqrt(1));
    }

    // 关键是如何处理无恰好相等值时的情况, 结果应该在较小的一边产生, 这样平方后的值才不会超过原始值
    public int mySqrt(int x) {
        int left = 0, right = x;
        int sqrt = 0;
        while (left <= right) {
            int middle = (left + right) / 2;
            long val = (long) middle * middle;
            if (val == x) {
                return middle;
            } else if (val < x) { // 偏小的，可取
                left = middle + 1;
                sqrt = middle;
            } else { // 偏大的, 超过原始值不可取
                right = middle - 1;
            }
        }

        return sqrt;
    }
}

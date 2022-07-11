package com.github.zhgxun.leetcode.math;

/**
 * 7. 整数反转
 * <p>
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 输入：x = 123
 * 输出：321
 * <p>
 * 输入：x = 120
 * 输出：21
 * <p>
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EasyReverseInt {

    public static void main(String[] args) {
        EasyReverseInt object = new EasyReverseInt();
        System.out.println(object.reverse(123));
        System.out.println(object.reverse(120));
        System.out.println(object.reverse(-123));
        System.out.println(object.reverse(1534236469));
    }

    // 注意是反转后一次扩展10倍即可
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            // 处理溢出 整数最大值末尾为7 最小值末尾为-8
            int remain = x % 10;
            // 如果超过最大值
            if (result > Integer.MAX_VALUE / 10 || result == Integer.MAX_VALUE / 10 && remain > 7) return 0;
            // 超出最小值
            if (result < Integer.MIN_VALUE / 10 || result == Integer.MIN_VALUE / 10 && remain < -8) return 0;
            result = result * 10 + remain;
            x /= 10;
        }
        return result;
    }
}

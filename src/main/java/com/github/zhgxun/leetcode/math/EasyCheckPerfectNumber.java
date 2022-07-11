package com.github.zhgxun.leetcode.math;

/**
 * 507. 完美数
 * <p>
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * <p>
 * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
 * <p>
 * 输入：28
 * 输出：True
 * 解释：28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EasyCheckPerfectNumber {

    public static void main(String[] args) {
        EasyCheckPerfectNumber object = new EasyCheckPerfectNumber();
        System.out.println(object.checkPerfectNumber(28));
    }

    /**
     * 枚举一个数的所有正因子数
     * <p>
     * 枚举的上限其实是 当前数字的一半 因为最大的正因子就是它的一半
     * 但是要注意跟它相等时, 正因子其实只算一个
     *
     * @param num 整数
     * @return 判断一个数是否为完美数
     */
    public boolean checkPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i != 0) {
                continue;
            }

            // 有一个 质因子 i 则对应有另一个 num / i 的质因子, 每次都是一对
            sum += i;
            if (i * i != num) {
                sum += num / i;
            }
        }
        return sum - num == num;
    }
}

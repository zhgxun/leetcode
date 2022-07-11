package com.github.zhgxun.leetcode.math;

import java.util.Arrays;

/**
 * 66. 加一
 * 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EasyPlusOne {

    public static void main(String[] args) {
        EasyPlusOne object = new EasyPlusOne();
        System.out.println(Arrays.toString(object.plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(object.plusOne(new int[]{9, 9, 9})));
    }

    // 需要注意的是 向前进位时超过原本长度的情形其实只有 999->1000 这种情形, 因为默认行为的关系, 只需要高位赋值即可
    public int[] plusOne(int[] digits) {
        int remain = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = i == digits.length - 1 ? digits[i] + 1 : digits[i] + remain;
            remain = num / 10;
            digits[i] = num % 10;
        }
        if (remain != 0) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        return digits;
    }
}

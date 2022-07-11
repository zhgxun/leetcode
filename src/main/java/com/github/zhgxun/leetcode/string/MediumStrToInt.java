package com.github.zhgxun.leetcode.string;

// https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
public class MediumStrToInt {

    public static void main(String[] args) {
        MediumStrToInt object = new MediumStrToInt();
//        System.out.println(object.strToInt("42"));
//        System.out.println(object.strToInt("   -42"));
//        System.out.println(object.strToInt("4193 with words"));
//        System.out.println(object.strToInt("words and 987"));
        System.out.println(object.strToInt("-91283472332"));
    }

    public int strToInt(String str) {
        // 去掉开头的空格
        int left = 0;
        while (left < str.length() && str.charAt(left) == ' ') left++;

        // 去掉结尾的空格
        int right = str.length() - 1;
        while (right >= left && str.charAt(right) == ' ') right--;

        // 是否是正负号
        int sign = 1;
        char c = str.charAt(left);
        if (c == '+' || c == '-') {
            if (c == '-') {
                sign = -1;
            }
            left++;
        }

        int result = 0;
        for (int i = left; i <= right; i++) {
            int num = str.charAt(i) - '0';
            if (num < 0 || num > 9) {
                break;
            }

            // 溢出有两个地方 result * 10 和 result * 10 + num
            // 正数
            // result * 10 >= max -> max / 10 <= result
            // result * 10 + num >= max -> (max - num) / 10 <= result
            //
            // 负数
            // result * 10 + num <= min -> (min - num) / 10 >= result
            if ((Integer.MAX_VALUE - num) / 10 <= result) {
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + num;
        }

        return result * sign;
    }
}

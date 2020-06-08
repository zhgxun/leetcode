package com.github.zhgxun.leetcode.string;

/**
 * 8. 字符串转换整数 (atoi)
 * <p>
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class MyAtoi {

    public static void main(String[] args) {
        System.out.println(new MyAtoi().myAtoi("        "));
        System.out.println(new MyAtoi().myAtoi("42"));
        System.out.println(new MyAtoi().myAtoi("+42"));
        System.out.println(new MyAtoi().myAtoi("-42"));
        System.out.println(new MyAtoi().myAtoi("  42"));
        System.out.println(new MyAtoi().myAtoi("42  "));
        System.out.println(new MyAtoi().myAtoi("4 2  "));
        System.out.println(new MyAtoi().myAtoi("-91283472332"));
    }

    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;

        // empty
        if (str == null || str.length() == 0) return 0;

        // remove space
        while (index < str.length() && str.charAt(index) == ' ') index++;

        // handle sign
        if (index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // convert number and avoid overflow
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;
            if ((total > (Integer.MAX_VALUE - digit) / 10)) {
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            total = total * 10 + digit;
            index++;
        }

        return total * sign;
    }

    public int myAtoiV2(String str) {
        int result = 0, len = str.length(), flag = 1;
        boolean isBlank = true;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (isBlank && c == ' ') {
            } else if (isBlank && c == '+') {
                isBlank = false;
                flag = 1;
            } else if (isBlank && c == '-') {
                isBlank = false;
                flag = -1;
            } else if (c >= '0' && c <= '9') {
                isBlank = false;
                // result = result * 10 + (c - '0') 可能提前越界故缩小判断
                if ((result > (Integer.MAX_VALUE - (c - '0')) / 10)) {
                    return flag > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                result = result * 10 + (c - '0');
            } else {
                break;
            }
        }

        return result * flag;
    }
}

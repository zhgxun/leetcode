package com.github.zhgxun.leetcode.math;

/**
 * 504. 七进制数
 * <p>
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 * <p>
 * 100 -> 202
 * -7 -> -10
 */
public class EasyConvertToBase7 {

    public static void main(String[] args) {
        EasyConvertToBase7 object = new EasyConvertToBase7();
        System.out.println(object.convertToBase7(-7));
        System.out.println(object.convertToBase7(100));
        System.out.println(object.convertToBase7(4312));

        // 哈哈哈, 执行用时 0ms 的范例
        System.out.println(Integer.toString(100, 7));
    }

    /**
     * 10 进制转化任意进制的思路都是除x取余，其中 x 为进制数，比如 2 进制就是 除 2 取余，7 进制就是除 7 取余
     *
     * @param num 十进制数
     * @return 字符串格式的余数
     */
    public String convertToBase7(int num) {
        if (num == 0) return "0";

        int flag = 1;
        if (num < 0) {
            flag = -1;
            num = -num;
        }

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }

        String result = sb.reverse().toString();
        return flag == 1 ? result : "-" + result;
    }
}

package com.github.zhgxun.leetcode.string;

import java.util.Arrays;

/**
 * 344. 反转字符串
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EasyReverseString {

    public static void main(String[] args) {
        EasyReverseString object = new EasyReverseString();
        char[] c = new char[]{'h', 'e', 'l', 'l', 'o'};
        object.reverseString(c);
        System.out.println(Arrays.toString(c));
    }

    // 交换
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left <= right) {
            char c = s[left];
            s[left++] = s[right];
            s[right--] = c;
        }
    }
}

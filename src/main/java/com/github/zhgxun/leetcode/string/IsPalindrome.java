package com.github.zhgxun.leetcode.string;

/**
 * 9. 回文数
 * <p>
 * https://leetcode-cn.com/problems/palindrome-number/
 * <p>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class IsPalindrome {

    public static void main(String[] args) {
        System.out.println(new IsPalindrome().validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }

    // cupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupucu
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            // 注意处理方式, 删除可能删除左边或者右边, 因此需要两次迭代
            if (l == r) {
                left++;
                right--;
            } else {
                // 尝试删除左边
                boolean leftFlag = true;
                for (int i = left + 1, j = right; i <= right; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        leftFlag = false;
                        break;
                    }
                }
                // 尝试删除右边
                boolean rightFlag = true;
                for (int i = left, j = right - 1; i <= right; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        rightFlag = false;
                        break;
                    }
                }

                return leftFlag || rightFlag;
            }
        }

        return true;
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }

        return x == reverse || x == reverse / 10;
    }

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            // 只考虑字符和数字
            char l = s.charAt(left);
            if (!(l >= '0' && l <= '9') && !(l >= 'a' && l <= 'z') && !(l >= 'A' && l <= 'Z')) {
                left++;
                continue;
            }

            char r = s.charAt(right);
            if (!(r >= '0' && r <= '9') && !(r >= 'a' && r <= 'z') && !(r >= 'A' && r <= 'Z')) {
                right--;
                continue;
            }

            // 转为小写字母
            if (l >= 'A' && l <= 'Z') {
                l += 32;
            }
            if (r >= 'A' && r <= 'Z') {
                r += 32;
            }
            System.out.println(l + " == " + r);
            if (l != r) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

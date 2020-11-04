package com.github.zhgxun.leetcode.string;

public class ConvertToTitle {

    public static void main(String[] args) {
        System.out.println(new ConvertToTitle().titleToNumber("A"));
        System.out.println(new ConvertToTitle().titleToNumber("B"));
        System.out.println(new ConvertToTitle().titleToNumber("AB"));
    }

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            n--;
            sb.append((char) (n % 26 + 'A'));
            n /= 26;
        }

        return sb.reverse().toString();
    }

    public int titleToNumber(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result * 26 + s.charAt(i) - 'A' + 1;
        }
        return result;
    }
}

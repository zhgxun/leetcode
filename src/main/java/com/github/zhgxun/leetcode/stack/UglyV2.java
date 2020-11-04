package com.github.zhgxun.leetcode.stack;

public class UglyV2 {
    public final int[] numbers = new int[1690];

    public UglyV2() {
        numbers[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < 1690; i++) {
            int ugly = Math.min(Math.min(numbers[i2] * 2, numbers[i3] * 3), numbers[i5] * 5);
            numbers[i] = ugly;
            if (numbers[i2] * 2 == ugly) i2++;
            if (numbers[i3] * 3 == ugly) i3++;
            if (numbers[i5] * 5 == ugly) i5++;
        }
    }
}

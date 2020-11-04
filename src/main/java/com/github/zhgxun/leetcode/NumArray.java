package com.github.zhgxun.leetcode;

import java.util.concurrent.locks.ReadWriteLock;

class NumArray {

    ReadWriteLock lock;

    public static void main(String[] args) {
        NumArray object = new NumArray(new int[]{1, 3, 5});
        object.lock.readLock().lock();
        object.lock.writeLock().lock();
        System.out.println(object.sumRange(0, 2));
        object.update(1, 2);
        System.out.println(object.sumRange(0, 2));
    }

    int[] sum;
    int[] nums;

    public NumArray(int[] nums) {
        if (nums.length == 0) return;
        this.nums = nums;
        sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public void update(int i, int val) {
        nums[i] = val;
        int j;
        if (i <= 1) {
            sum[0] = nums[0];
            j = 1;
        } else {
            j = i - 1;
        }
        for (; j < nums.length; j++) {
            sum[j] = sum[j - 1] + nums[j];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j] - (i > 0 ? sum[i - 1] : 0);
    }
}

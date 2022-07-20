package com.github.zhgxun.leetcode.array;

// https://leetcode.cn/problems/sum-of-all-odd-length-subarrays/
public class EasySumOddLengthSubarrays {

    public static void main(String[] args) {
        EasySumOddLengthSubarrays object = new EasySumOddLengthSubarrays();
        System.out.println(object.sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int[] prefixSum = new int[arr.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }

        int sum = 0;

        for (int step = 1; step <= arr.length; step += 2) {
            for (int i = 0; i < arr.length; i++) {
                int end = i + step;
                if (end > arr.length) {
                    break;
                }
                sum += (prefixSum[end] - prefixSum[i]);
            }
        }
        return sum;
    }

    public int sumOddLengthSubarraysV1(int[] arr) {
        int sum = 0;

        for (int step = 1; step <= arr.length; step += 2) {
            for (int i = 0; i < arr.length; i++) {
                int end = i + step;
                if (end > arr.length) {
                    break;
                }
                // 这部分优化为前缀和
                int j = i;
                for (; j < end; j++) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }
}

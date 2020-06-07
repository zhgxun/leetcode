package com.github.zhgxun.leetcode.array;

/**
 * 面试题51. 数组中的逆序对
 * <p>
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class ReversePairs {

    public static void main(String[] args) {
        int[] t = new int[]{1, 3, 2, 3, 1};
        System.out.println(new ReversePairs().reversePairs(t));
    }

    public int reversePairs(int[] nums) {
        return merge(nums, 0, nums.length - 1);
    }

    public int merge(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int count = 0;
        int middle = (left + right) / 2;
        count += merge(nums, left, middle) + merge(nums, middle + 1, right);
        // 需要在这里统计计数, 合并过程统计计算比较麻烦单独统计一次
        int l = left;
        int r = middle + 1;
        while (l <= middle && r <= right) {
            // 要处理溢出
            if ((long) nums[l] > 2 * (long) nums[r]) {
                count += middle - l + 1;
                r++;
            } else {
                l++;
            }
        }

        // 合并两部分有序数组
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = middle + 1;
        int index = 0;
        while (i <= middle && j <= right) {
            temp[index++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= middle) temp[index++] = nums[i++];
        while (j <= right) temp[index++] = nums[j++];

        System.arraycopy(temp, 0, nums, left, temp.length);
        return count;
    }

    // 两部分有序故逆序对个数是比它小的元素个数
    int mergeV2(int[] arr, int start, int end) {
        if (start >= end) return 0;
        int mid = (start + end) / 2;
        int count = mergeV2(arr, start, mid) + mergeV2(arr, mid + 1, end);

        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            count += arr[i] <= arr[j] ? 0 : mid - i + 1;
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, start, end - start + 1);
        return count;
    }
}

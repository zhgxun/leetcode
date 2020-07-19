package com.github.zhgxun.leetcode.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindPairs {

    public static void main(String[] args) {
        System.out.println(new FindPairs().maximumProduct(new int[]{1, 2, 3, 4, -3, -3}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)) map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) result.add(entry.getValue());
        return result;
    }

    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length <= 2) return 0;

        // 最大的三个数
        int one = Integer.MIN_VALUE, two = one, three = two;

        // 最小的两个数
        int five = Integer.MAX_VALUE, four = five;
        for (int num : nums) {
            // 投票最大
            if (num >= one) {
                three = two;
                two = one;
                one = num;
            } else if (num >= two) {
                three = two;
                two = num;
            } else if (num >= three) {
                three = num;
            }

            // 投票最小
            if (num <= five) {
                four = five;
                five = num;
            } else if (num <= four) {
                four = num;
            }
        }

        return Math.max(one * two * three, one * four * five);
    }

    public String[] findRestaurant(String[] list1, String[] list2) {

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) map.put(list1[i], i);

        int minIndexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            String word = list2[i];
            if (map.containsKey(word)) {
                minIndexSum = Math.min(minIndexSum, i + map.get(word));
            }
        }

        if (minIndexSum == Integer.MIN_VALUE) return new String[0];

        List<String> res = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            String word = list2[i];
            if (map.containsKey(word) && map.get(word) + i == minIndexSum) {
                res.add(list2[i]);
            }
        }

        String[] result = new String[res.size()];
        for (int i = 0; i < res.size(); i++) result[i] = res.get(i);

        return result;
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";

        char[] chars = s.toCharArray();
        int length = chars.length;

        int start = 0;
        while (start < length && chars[start] == ' ') start++;
        if (start == length - 1) return "";

        int end = length - 1;
        while (end >= 0 && chars[end] == ' ') end--;
        if (end == 0) return "";

        int begin = start;
        for (int i = start; i <= end; i++) {
            // 找到一个单词或者到有效结尾
            if (chars[i] == ' ' || (i == end && i > begin)) {
                int left = begin, right = i == end ? i : i - 1;
                while (left <= right) {
                    char temp = chars[left];
                    chars[left++] = chars[right];
                    chars[right--] = temp;
                }
                begin = i + 1;
            }
        }

        return new String(chars);
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            // 最多反转k个或者少于k个均符合条件
            int left = i, right = i + k - 1;
            if (right >= chars.length - 1) right = chars.length - 1;
            while (left <= right) {
                char temp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = temp;
            }
        }
        return new String(chars);
    }

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < T.length; i++) {
            while (!deque.isEmpty() && T[i] > T[deque.getFirst()]) {
                res[deque.getFirst()] = i - deque.removeFirst();
            }
            deque.addFirst(i);
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = nums.length * 2 - 1; i >= 0; i--) {
            int index = i % nums.length;
            while (!deque.isEmpty() && nums[index] >= nums[deque.getFirst()]) {
                deque.removeFirst();
            }
            res[index] = !deque.isEmpty() ? nums[deque.getFirst()] : -1;
            deque.addFirst(index);
        }
        return res;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2 == null || nums2.length == 0) return new int[0];

        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            // 出栈的元素找到属于它的下一个最大值即当前元素
            while (!deque.isEmpty() && nums2[i] > nums2[deque.getFirst()]) {
                map.put(nums2[deque.removeFirst()], nums2[i]);
            }
            deque.addFirst(i);
        }
        int[] res = new int[nums1.length];
        int index = 0;
        for (int num : nums1) res[index++] = map.getOrDefault(num, -1);
        return res;
    }

    public int findPairs(int[] nums, int k) {
        // 如果 y = x - k (1 = 3 - 2) 在 visited 中，则在 find 中加入较小值 x - k = 1
        // 如果 x = y + k (3 = 1 + 2)在 visited 中，则在 find 中加入较小值 y = 1
        Set<Integer> visited = new HashSet<>();
        Set<Integer> find = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(num - k)) find.add(num - k);
            if (visited.contains(num + k)) find.add(num);
            visited.add(num);
        }

        return find.size();
    }
}

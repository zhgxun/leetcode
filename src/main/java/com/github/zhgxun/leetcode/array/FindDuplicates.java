package com.github.zhgxun.leetcode.array;

import com.github.zhgxun.leetcode.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FindDuplicates {

    public static void main(String[] args) {
        int[][] res = new FindDuplicates().kClosest(new int[][]{new int[]{3, 3}, new int[]{5, -1}, new int[]{-2, 4}}, 2);
        for (int[] ints : res) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        int[] temp = new int[points.length];
        for (int i = 0; i < points.length; i++) temp[i] = length(points[i]);
        Arrays.sort(temp);
        int[][] res = new int[K][2];
        int index = 0;
        for (int[] ints : points) if (length(ints) <= temp[K - 1]) res[index++] = ints;
        return res;
    }

    private int length(int[] ints) {
        return ints[0] * ints[0] + ints[1] * ints[1];
    }

    public int[][] kClosestV2(int[][] points, int K) {
        PriorityQueue<Point> heap = new PriorityQueue<>((v1, v2) -> (int) ((v2.length() - v1.length()) * 10000000));
        for (int[] ints : points) {
            heap.add(new Point(ints));
            if (heap.size() > K) heap.poll();
        }

        int[][] res = new int[K][2];
        while (!heap.isEmpty()) {
            Point point = heap.poll();
            res[--K] = new int[]{point.x, point.y};
        }

        return res;
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int[] point) {
            this.x = point[0];
            this.y = point[1];
        }

        public Double length() {
            return Math.sqrt(Math.pow(Math.abs(x) * 1.0, 2) + Math.pow(Math.abs(y) * 1.0, 2));
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);

        // 维护k个数量的堆
        PriorityQueue<String> heap = new PriorityQueue<>((v1, v2) -> map.get(v1).equals(map.get(v2)) ? v2.compareTo(v1) : map.get(v1) - map.get(v2));
        for (String word : map.keySet()) {
            heap.add(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> res = new ArrayList<>();
        while (!heap.isEmpty()) res.add(heap.poll());
        Collections.reverse(res);
        return res;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((v1, v2) -> v2.getValue() - v1.getValue());
        heap.addAll(map.entrySet());
        int[] res = new int[k];
        while (!heap.isEmpty() && k > 0) res[--k] = heap.poll().getKey();
        return res;
    }

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) map.put(ch, map.getOrDefault(ch, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>((v1, v2) -> v2.getValue() - v1.getValue());
        heap.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> entry = heap.poll();
            sb.append(entry.getKey().toString().repeat(entry.getValue()));
        }
        return sb.toString();
    }

    public char findTheDifference(String s, String t) {
        int[] res = new int[26];
        for (char ch : s.toCharArray()) res[ch - 'a']++;
        for (char ch : t.toCharArray()) res[ch - 'a']--;
        for (int i = 0; i < 26; i++) if (res[i] != 0) return (char) (i + 'a');
        return ' ';
    }

    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;
        while (left <= right) {
            int middle = (left + right) / 2;
            long val = (long) middle * middle;
            if (val == num) return true;
            else if (val > num) right = middle - 1;
            else left = middle + 1;
        }
        return false;
    }

    public int repeatedStringMatch(String A, String B) {
        // 增加无效字符的判断速度从大约320ms降低至3ms，说明测试用例中有大量的无效字符串存在
        int[] count = new int[26];
        for (char ch : A.toCharArray()) {
            count[ch - 'a']++;
        }
        for (char ch : B.toCharArray()) {
            if (count[ch - 'a'] == 0) return -1;
        }

        // 重复次数判断即可
        int times = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(A);
        while (sb.length() < B.length()) {
            times++;
            sb.append(A);
        }

        if (sb.toString().contains(B)) return times;
        else if (sb.append(A).toString().contains(B)) return times + 1;
        else return -1;
    }

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        // 重复次数至少为1次则折半
        for (int i = len / 2; i >= 1; i--) {
            if (len % i != 0) continue; // 取余不为0则无法构成重复子串
            // 累计重复次数后应该等于原字符串
            if (s.substring(0, i).repeat(len / i).equals(s)) return true;
        }

        return false;
    }

    Deque<Integer> deque = new ArrayDeque<>();

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        ListNode left = reverse(l1);
        ListNode right = reverse(l2);
        int remain = 0;
        while (left != null || right != null) {
            int a = left != null ? left.val : 0;
            int b = right != null ? right.val : 0;

            int sum = a + b + remain;

            remain = sum / 10;

            node.next = new ListNode(sum % 10);
            node = node.next;

            if (left != null) left = left.next;
            if (right != null) right = right.next;
        }

        if (remain > 0) {
            node.next = new ListNode(remain);
        }

        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode head) {
        ListNode node = null, temp = head, next;
        while (temp != null) {
            next = temp.next;
            temp.next = node;
            node = temp;

            temp = next;
        }

        return node;
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        for (int i = 0; i < nums.length; i++) {
            while (i + 1 != nums[i] && nums[i] + 1 != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        System.out.println(Arrays.toString(nums));

        return null;
    }
}

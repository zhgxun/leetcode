package com.github.zhgxun.leetcode.stack;

import com.github.zhgxun.leetcode.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinStackV2 {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        // 快慢指针找中点
        ListNode slow = head, fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转后半段链表
        ListNode temp = slow, dummy = null, next;
        while (temp != null) {
            next = temp.next;
            temp.next = dummy;
            dummy = temp;

            temp = next;
        }

        // 两端链表相比较
        ListNode node = head;
        while (node != null && dummy != null) {
            if (node.val != dummy.val) return false;
            node = node.next;
            dummy = dummy.next;
        }

        return true;
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        int cand1 = nums[0], cand2 = nums[0], count1 = 0, count2 = 0;
        for (int value : nums) {
            if (cand1 == value) {
                count1++;
                continue;
            }

            if (cand2 == value) {
                count2++;
                continue;
            }

            if (count1 == 0) {
                cand1 = value;
                count1++;
                continue;
            }

            if (count2 == 0) {
                cand2 = value;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }

        count1 = count2 = 0;
        for (int num : nums) {
            if (num == cand1) count1++;
            else if (num == cand2) count2++;
        }

        if (count1 > nums.length / 3) res.add(cand1);
        if (count2 > nums.length / 3) res.add(cand2);

        return res;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;

        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (Map.Entry<Long, Long> entry : map.entrySet()) {
                long val = entry.getKey();
                long index = entry.getValue();
                if (Math.abs(nums[i] - val) <= (long) t && Math.abs(index - i) <= (long) k) return true;
            }
            map.put((long) nums[i], (long) i);
        }

        return false;
    }

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        //if (nums.length == 2) return nums[0] > nums[1] ? 0 : 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1] && (i + 1 >= nums.length || nums[i] > nums[i + 1])) return i;
        }
        return 0;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        ListNode cur = head, temp = dummy, next;
        while (cur != null) {
            next = cur.next;

            // 找到 cur 即将插入位置的上一个节点
            while (temp.next != null && temp.next.val < cur.val) temp = temp.next;

            cur.next = temp.next;
            temp.next = cur;

            temp = dummy; // 始终记录头节点

            cur = next;
        }

        return dummy.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int aLen = 0, bLen = 0;
        ListNode node = headA;
        while (node != null) {
            aLen++;
            node = node.next;
        }

        node = headB;
        while (node != null) {
            bLen++;
            node = node.next;
        }

        ListNode aNode = headA, bNode = headB;
        int diff = Math.abs(aLen - bLen);
        if (aLen > bLen) {
            while (diff-- > 0) aNode = aNode.next;
        } else {
            while (diff-- > 0) bNode = bNode.next;
        }

        while (aNode != null && bNode != null) {
            if (aNode == bNode) return aNode;
            aNode = aNode.next;
            bNode = bNode.next;
        }

        return null;
    }

    /**
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     */
    public static void main(String[] args) {
        MinStackV2 minStack = new MinStackV2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.stack);
        System.out.println(minStack.min);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    private final Deque<Integer> stack = new ArrayDeque<>();
    private final Deque<Integer> min = new ArrayDeque<>();

    /**
     * initialize your data structure here.
     */
    public MinStackV2() {

    }

    public void push(int x) {
        stack.addFirst(x);
        if (min.isEmpty()) {
            min.addFirst(x);
        } else if (min.peekFirst() < x) {
            min.addFirst(min.peekFirst());
        } else {
            min.addFirst(x);
        }
    }

    public void pop() {
        stack.removeFirst();
        min.removeFirst();
    }

    public int top() {
        return stack.peekFirst();
    }

    public int getMin() {
        return min.peekFirst();
    }
}

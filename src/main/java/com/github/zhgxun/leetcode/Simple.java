package com.github.zhgxun.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Simple {

    public static void main(String[] args) throws Exception {
    }

    public int closedIsland(int[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0) return count;
        int colLen = grid.length;
        int rowLen = grid[0].length;
        for (int col = 1; col < colLen; col++) {
            for (int row = 1; row < rowLen; row++) {
                if (grid[col][row] == 0) {
                    if (dfs(grid, col, row)) count++;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid, int col, int row) {
        int colLen = grid.length;
        int rowLen = grid[0].length;
        if (col < 0 || col >= colLen || row < 0 || row >= rowLen) return false;
        if (grid[col][row] == 1) return true;

        grid[col][row] = 1;

        boolean up = dfs(grid, col - 1, row);
        boolean right = dfs(grid, col, row + 1);
        boolean down = dfs(grid, col + 1, row);
        boolean left = dfs(grid, col, row - 1);
        return up && right && down && left;
    }

    private int[] days, costs, cache;
    private final int[] duration = new int[]{1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        this.cache = new int[days.length];
        return dfs(0);
    }

    private int dfs(int day) {
        if (day >= days.length) return 0;
        if (cache[day] != 0) return cache[day];
        int cur = day;
        cache[day] = Integer.MAX_VALUE;
        for (int i = 0; i < duration.length; i++) {
            while (cur < days.length && days[cur] < days[day] + duration[i]) cur++;
            cache[day] = Math.min(cache[day], dfs(cur) + costs[i]);
        }
        return cache[day];
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        //mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSort(int[] nums, int left, int right) {
        // 排序结束的条件左边界超出右边界
        if (left >= right) return;

        // 以有边界为中点元素
        int index = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] < nums[right]) {
                int temp = nums[index];
                nums[index++] = nums[i];
                nums[i] = temp;
            }
        }

        // 放置真正的中点元素
        int temp = nums[index];
        nums[index] = nums[right];
        nums[right] = temp;

        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }

    public void mergeSort(int[] a, int left, int right) {
        if (left >= right) return;
        int middle = (left + right) / 2;
        mergeSort(a, left, middle);
        mergeSort(a, middle + 1, right);
        merge(a, left, middle, right);
    }

    public void merge(int[] a, int left, int middle, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = middle + 1, index = 0;
        while (i <= middle && j <= right) {
            temp[index++] = a[i] >= a[j] ? a[j++] : a[i++];
        }

        while (i <= middle) temp[index++] = a[i++];
        while (j <= right) temp[index++] = a[j++];

        System.arraycopy(temp, 0, a, left, temp.length);
    }

    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 考虑本身满足条件的情况
        int sum = 0, res = 0;
        for (int num : A) {
            sum += num;
            int mod = (sum % K + K) % K;
            int count = map.getOrDefault(mod, 0);
            res += count;
            map.put(mod, count + 1);
        }
        return res;
    }

    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        if (k <= 1) return 0;

        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + Math.log(nums[i]);
        }

        int ans = 0;
        for (int i = 0; i < prefix.length; i++) {
            // 二分查找提高时间复杂度比硬找快的速度是数量级差异的
            int left = i + 1, right = prefix.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (prefix[mid] - prefix[i] < logk - 1e-9) left = mid + 1;
                else right = mid - 1;
            }
            ans += left - i - 1;
        }
        return ans;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;

        int left = 0, count = 0, prod = 1;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            // 乘积比k小的本来就满足
            // 比k大的需要找到符合条件的子数组
            // 乘积也是递增(至少相等), 所以往后移动left指针, 查找结束为止, 之前的子数组都满足条件
            while (prod >= k) prod /= nums[left++];
            count += right - left + 1;
        }
        return count;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) return true;
            } else map.put(sum, i);
        }
        return false;
    }

    public boolean checkSubarraySum1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;

        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (k == 0) {
                    if (sum == 0) return true;
                } else if (sum % k == 0) return true;
            }
        }

        return false;
    }

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int count = 0, preSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            preSum += num;
            if (map.containsKey(preSum - k)) count += map.get(preSum - k);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }

        return count;
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] needs = new int[26];
        for (char ch : s1.toCharArray()) {
            needs[ch - 'a']++;
        }
        int targetLen = 0;
        for (int i = 0; i < 26; i++) if (needs[i] > 0) targetLen++;

        int findLen = 0, left = 0;
        int[] window = new int[26];
        char[] chars = s2.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            int num = chars[right] - 'a';
            window[num]++;
            if (window[num] == needs[num]) findLen++;
            while (right - left + 1 == s1.length()) {
                if (findLen == targetLen) return true;
                int leftNum = chars[left] - 'a';
                if (window[leftNum] == needs[leftNum]) findLen--;
                window[leftNum]--;
                left++;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> map = new HashSet<>(wordDict);
        List<String>[] dp = new LinkedList[s.length() + 1];
        List<String> res = new LinkedList<>();
        res.add("");
        dp[0] = res;
        for (int i = 1; i <= s.length(); i++) {
            List<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) { // 枚举i开始的所有有效单词列表
                // j 已经存在有效单词, 当前 substring(j, i) 也是有效单词则枚举结果集
                if (dp[j].size() > 0 && map.contains(s.substring(j, i))) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            // 存储位置i的有效单词字符串
            dp[i] = list;
        }
        return dp[s.length()];
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) { // 这个构成时间复杂度 O(n^2)
                // dp[j] 已经构成过有效单词能否在构成有效单词, 继续判断 substring(j, i) 是否为有效单词
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean dfs(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (root.val != head.val) return false;
        return dfs(head.next, root.left) || dfs(head.next, root.right);
    }

    public int maxPower(String s) {
        int len = s.length();
        if (len == 1) return 1;

        int slow = 0, fast = 1, maxLen = 0;
        while (slow < len && fast < len) {
            if (s.charAt(slow) != s.charAt(fast)) maxLen = Math.max(maxLen, fast - slow++);
            else fast++;
        }
        if (fast == len) maxLen = Math.max(maxLen, fast - slow);

        return maxLen;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return new ArrayList<>();

        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        inOrder(root1, left);
        inOrder(root2, right);
        if (left.size() == 0) return right;
        if (right.size() == 0) return left;

        return merge(left, right);
    }

    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            result.add(left.get(i) >= right.get(j) ? right.get(j++) : left.get(i++));
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int sum = 0;
        ListNode node = dummy;
        while (node != null) {
            sum += node.val;
            map.put(sum, node);
            node = node.next;
        }

        sum = 0;
        node = dummy;
        while (node != null) {
            sum += node.val;
            node.next = map.get(sum).next;
            node = node.next;
        }

        return dummy.next;
    }

    public String removeDuplicates(String S) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < S.length(); i++) {
            boolean isDelete = false;
            while (!deque.isEmpty() && S.charAt(i) == deque.getFirst()) {
                deque.removeFirst();
                isDelete = true;
            }
            if (!isDelete) deque.addFirst(S.charAt(i));
        }
        char[] chars = new char[deque.size()];
        for (int i = deque.size() - 1; i >= 0; i--) chars[i] = deque.removeFirst();
        return new String(chars);
    }

    public int[] nextLargerNodes(ListNode head) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }

        int[] nums = new int[len];
        int index = 0;
        node = head;
        while (node != null) {
            nums[index++] = node.val;
            node = node.next;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.getFirst()]) {
                result[deque.removeFirst()] = nums[i];
            }
            deque.addFirst(i);
        }
        return result;
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int one = nums[0], two = Integer.MIN_VALUE, index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == one || nums[i] == two) continue;
            if (nums[i] > one) {
                two = one;
                one = nums[i];
                index = i;
            } else if (nums[i] > two) {
                two = nums[i];
            }
        }

        return (one == 0 || two == 0 || one >= 2 * two) ? index : -1;
    }

    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return new int[0];

        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!deque.isEmpty() && T[i] > T[deque.getFirst()]) {
                int index = deque.removeFirst();
                res[index] = i - index;
            }
            deque.addFirst(i);
        }

        return res;
    }

    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode node = root;
        while (node != null) {
            len++;
            node = node.next;
        }

        int num = len / k;
        int remain = len % k;

        node = root;
        ListNode[] nodes = new ListNode[k];
        ListNode temp, tempHead;
        for (int i = 0; i < k; i++) { // k 段链表
            temp = new ListNode(0);
            tempHead = temp;
            for (int j = 0; j < num + (remain > 0 ? 1 : 0) && node != null; j++) {
                temp.next = node;
                temp = temp.next;
                node = node.next;
            }
            temp.next = null;
            remain--;

            nodes[i] = tempHead.next;
        }

        return nodes;
    }

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int sum = 0, leftSum = 0;
        for (int num : nums) sum += num;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum * 2 + nums[i] == sum) return i;
            leftSum += nums[i];
        }
        return -1;
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxLen = 1, len = 1;
        for (int i = 0; i < nums.length; i++) {
            len++;
            if (i >= 1 && nums[i] <= nums[i - 1]) {
                maxLen = Math.max(maxLen, len - 1);
                len = 1;
            }
        }

        return Math.max(maxLen, len);
    }

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && i + 1 != nums[i] && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 1; i <= nums.length; i++) if (nums[i - 1] != i) return i;

        return 0;
    }

    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)) res[1] = i;
            else if (map.get(i) == 2) res[0] = i;
        }

        return res;
    }

    public int[] findErrorNumsV2(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                res[0] = Math.abs(nums[i]);
                continue;
            }
            nums[index] = -nums[index];
        }

        for (int i = 1; i <= nums.length; i++) if (nums[i - 1] >= 0) res[1] = i;

        return res;
    }

    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return 0.0;

        double sum = 0, maxSum;
        for (int i = 0; i < k; i++) sum += nums[i];
        maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum -= nums[i - k];
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum / k;
    }

    public boolean judgeSquareSum(int c) {
        for (double i = 0; i * i <= c; i++) {
            double b = Math.sqrt(c - i * i);
            if (b == (int) b) return true;
        }
        return false;
    }

    private final List<String> result = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return result;
        dfs(root, "");
        return result;
    }

    private void dfs(TreeNode root, String path) {
        if (root == null) return;
        path += path.length() == 0 ? root.val : "->" + root.val;
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }
        dfs(root.left, path);
        dfs(root.right, path);
    }

    private final Deque<Integer> deque = new ArrayDeque<>();

    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            clean(nums, i, k);
            deque.addFirst(i);
        }
        int index = 0;
        res[index++] = nums[deque.getLast()];

        for (int i = k; i < nums.length; i++) {
            clean(nums, i, k);
            deque.addFirst(i);
            res[index++] = nums[deque.getLast()];
        }

        return res;
    }

    private void clean(int[] nums, int index, int k) {
        // 超过队列长度则移除最后添加的元素
        if (!deque.isEmpty() && deque.peekLast() == index - k) deque.removeLast();

        // 保留队列里面的递增性
        while (!deque.isEmpty() && nums[deque.getFirst()] < nums[index]) deque.removeFirst();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>(k);
        int index = 0;
        for (int i = 0; i < k; i++) {
            clean(queue, nums, i, k);
            queue.addLast(i);
        }
        res[index++] = nums[queue.getFirst()];
        for (int i = k; i < nums.length; i++) {
            clean(queue, nums, i, k);
            queue.addLast(i);
            res[index++] = nums[queue.getFirst()];
        }

        return res;

    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        ListNode cur = head, pre = dummy, next = null;
        while (cur != null) {
            next = cur.next;

            // 找到 cur 即将插入位置的上一个节点
            //           cur
            // --pre-->      ---> pre.next
            while (pre.next != null && pre.next.val < cur.val) pre = pre.next;

            cur.next = pre.next;
            pre.next = cur;

            // 始终记录头节点
            pre = dummy;

            cur = next;
        }

        return dummy.next;
    }

    public void clean(ArrayDeque<Integer> queue, int[] nums, int i, int k) {
        // 队列满则移除队列头元素
        if (!queue.isEmpty() && queue.getFirst() == i - k) {
            queue.removeFirst();
        }

        // 在有效队列中, 如果当前值比已有元素都大则保留最大值
        while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) {
            queue.removeLast();
        }
    }

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        int[] res = new int[nums.length];
        int len = nums.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i % len] >= nums[stack.peek() % len]) {
                stack.pop();
            }
            res[i % len] = stack.isEmpty() ? -1 : nums[stack.peek() % len];
            stack.push(i);
        }

        return res;
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int len = 0;
        int num = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len) {
                len = entry.getValue();
                num = entry.getKey();
            }
        }
        return num;
    }

    public double myPow(double x, int n) {
        double res = 1;
        boolean flag = false;
        if (n < 0) {
            n = -n;
            flag = true;
        }


        return flag ? 1 / res : res;
    }

    public boolean isPowerOfTwo(int n) {
        if (n == 1) {
            return true;
        }
        int result = 1;
        for (; ; ) {
            result *= 2;
            System.out.println(result);
            if (result == n) {
                return true;
            } else if (result > n) {
                return false;
            }
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        helper(result, 0, root);
        return result;
    }

    public void helper(List<List<Integer>> result, int level, TreeNode root) {
        if (result.size() == level) {
            result.add(level, new ArrayList<>());
        }
        result.get(level).add(root.val);
        if (root.left != null) {
            helper(result, level + 1, root.left);
        }
        if (root.right != null) {
            helper(result, level + 1, root.right);
        }
    }
}

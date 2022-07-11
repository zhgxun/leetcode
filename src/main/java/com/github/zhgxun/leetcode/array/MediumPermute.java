package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * <pre>
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * </>
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MediumPermute {

    public static void main(String[] args) {
        MediumPermute object = new MediumPermute();
        System.out.println(object.permute(new int[]{1, 2, 3}));
    }

    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return result;
        dfs(nums, new boolean[nums.length], new ArrayList<>());
        return result;
    }

    /**
     * 深度优先搜索所有结果集
     * 枚举每一个值的情况
     * <pre>
     *      1
     *     /\
     *    2 3
     *   /  \
     *  3    2
     * <pre/>
     * 该问题的复杂度分析
     * 复杂度其实就是结果集个数, 即阶乘复杂度 O(n * n!)
     * @param nums 目标数组
     * @param visited 记录当前被访问过的元素
     * @param current 记录当前已有的目标元素
     */
    private void dfs(int[] nums, boolean[] visited, List<Integer> current) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            current.add(nums[i]);
            dfs(nums, visited, current);
            visited[i] = false;
            current.remove(current.size() - 1);
        }
    }
}

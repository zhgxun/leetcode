package com.github.zhgxun.leetcode.linkedlist;

import com.github.zhgxun.leetcode.ListNode;
import com.github.zhgxun.leetcode.ListNodeUtil;

/**
 * 82. 删除排序链表中的重复元素 II
 * <p>
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * <p>
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MediumDeleteDuplicates {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        MediumDeleteDuplicates object = new MediumDeleteDuplicates();
        ListNodeUtil.print(object.deleteDuplicates(head));
    }

    // 解题的关键是怎么表达当前元素是否要保留, 可以借助一个临时变量记录当前元素是否参与删除动作, 比如 3->3->4->4
    // 这样的元素布局是不能把 3->4 时的3存入目标元素中
    public ListNode deleteDuplicates(ListNode head) {
        // 定义一个新链表存储目标链表节点内容
        ListNode dummy = new ListNode(0);
        ListNode temp = head, dummyHead = dummy;
        boolean move = false;
        while (temp != null) {
            // 当前值跟下一个值相等则认为是需要删除的部分
            while (temp.next != null && temp.val == temp.next.val) {
                move = true;
                temp = temp.next;
            }
            // 没有元素被删除则说明当前元素是需要保存的目标元素
            if (!move) {
                dummy.next = temp;
                dummy = dummy.next;
            }

            temp = temp.next;
            move = false;
        }
        dummy.next = null;

        return dummyHead.next;
    }
}

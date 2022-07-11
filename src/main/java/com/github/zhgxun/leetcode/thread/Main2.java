package com.github.zhgxun.leetcode.thread;

import com.github.zhgxun.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main2 {

    private final Object object = new Object();
    private final Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        Main2 o = new Main2();

        new Thread(() -> {
            int count = 10;
            while (count > 0) {
                o.deque.addFirst(count--);
            }
        }).start();

        new Thread(() -> {
            while (!o.deque.isEmpty()) {
                synchronized (o.object) {
                    while (!o.deque.isEmpty() && o.deque.peekFirst() % 2 != 0) {
                        try {
                            o.object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + o.deque.removeFirst());
                    o.object.notify();
                }
            }
        }).start();
        new Thread(() -> {
            while (!o.deque.isEmpty()) {
                synchronized (o.object) {
                    while (!o.deque.isEmpty() && o.deque.peekFirst() % 2 != 1) {
                        try {
                            o.object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + o.deque.removeFirst());
                    o.object.notify();
                }
            }
        }).start();
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !deque.isEmpty()) {

            while (curr != null) {
                deque.addFirst(curr);
                curr = curr.left;
            }

            curr = deque.removeFirst();
            result.add(curr.val);
            curr = curr.right;
        }
        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollFirst();
            result.add(node.val);
            if (node.right != null) {
                deque.addFirst(node.right);
            }
            if (node.left != null) {
                deque.addFirst(node.left);
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.removeFirst();
            result.addFirst(node.val);
            if (node.left != null) {
                deque.addFirst(node.left);
            }
            if (node.right != null) {
                deque.addFirst(node.right);
            }
        }

        return result;
    }
}

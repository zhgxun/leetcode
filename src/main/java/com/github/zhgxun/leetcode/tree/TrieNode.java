package com.github.zhgxun.leetcode.tree;

import java.util.Arrays;

/**
 * 字典树节点
 */
public class TrieNode {

    private final TrieNode[] links = new TrieNode[26];

    private boolean isEnd;

    public TrieNode() {
    }

    // 字符是否包含
    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    // 获取字符
    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    // 将字符加入字典树中
    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    // 设置单词结束
    public void setEnd() {
        this.isEnd = true;
    }

    // 单词是否结束
    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "links=" + Arrays.toString(links) +
                ", isEnd=" + isEnd +
                '}';
    }
}

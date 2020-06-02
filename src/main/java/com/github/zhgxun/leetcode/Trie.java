package com.github.zhgxun.leetcode;

/**
 * 208. 实现 Trie (前缀树)
 * <p>
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 * <p>
 * Trie 树中最常见的两个操作是键的插入和查找
 */
public class Trie {

    private final TrieNode root = new TrieNode();

    public Trie() {
    }

    // 往字典树中插入一个字符
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            // 注意要把下一个节点返回出来, 否则返回空
            node = node.get(ch);
        }
        node.setEnd();
    }

    // 单词是否存在字典树中
    public boolean search(String word) {
        TrieNode node = startsPrefix(word);
        System.out.println("node: " + node);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        return startsPrefix(prefix) != null;
    }

    // 搜索单词前缀
    public TrieNode startsPrefix(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!node.containsKey(ch)) {
                return null;
            }
            node = node.get(ch);
        }
        return node;
    }

    @Override
    public String toString() {
        return "Trie{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie);
        System.out.println(trie.search("apple"));
        /**
         * trie.insert("apple");
         * trie.search("apple");   // 返回 true
         * trie.search("app");     // 返回 false
         * trie.startsWith("app"); // 返回 true
         * trie.insert("app");
         * trie.search("app");     // 返回 true
         */
    }
}

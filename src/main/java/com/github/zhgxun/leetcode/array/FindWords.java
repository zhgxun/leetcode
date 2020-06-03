package com.github.zhgxun.leetcode.array;

import java.util.*;

/**
 * 212. 单词搜索 II
 * <p>
 * https://leetcode-cn.com/problems/word-search-ii/
 */
public class FindWords {

    private final Trie trie = new Trie();
    private final Set<String> result = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return new ArrayList<>();
        }

        // 将单词构造为一棵前缀树
        for (String word : words) {
            trie.insert(word);
        }

        // dfs
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                StringBuilder sb = new StringBuilder();
                dfs(board, col, row, sb);
            }
        }

        return new ArrayList<>(result);
    }

    public void dfs(char[][] board, int col, int row, StringBuilder sb) {
        if (col < 0 || col >= board.length || row < 0 || row >= board[0].length) {
            return;
        }
        if (board[col][row] == '*') {
            return;
        }

        sb.append(board[col][row]);
        TrieNode node = trie.startsPrefix(sb.toString());
        if (node != null) {
            if (node.getIsEnd()) {
                result.add(sb.toString());
            }

            char ch = board[col][row];
            board[col][row] = '*';

            dfs(board, col - 1, row, sb);
            dfs(board, col, row + 1, sb);
            dfs(board, col + 1, row, sb);
            dfs(board, col, row - 1, sb);
            board[col][row] = ch;
        }

        // 注意这个是全局变量不管如何处理都要把当前层清理掉, 否则后续就全都错误匹配了
        sb.deleteCharAt(sb.length() - 1);
    }
}

class Trie {
    private final TrieNode root = new TrieNode();

    // 单词插入字典树
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setIsEnd();
    }

    // 是否是单词前缀
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
}

class TrieNode {
    private final TrieNode[] links = new TrieNode[26];
    private boolean isEnd;

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setIsEnd() {
        this.isEnd = true;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "links=" + Arrays.toString(links) +
                ", isEnd=" + isEnd +
                '}';
    }
}

package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 127. 单词接龙
 * <p>
 * https://leetcode-cn.com/problems/word-ladder/
 */
public class LadderLength {

    public static void main1(String[] args) {
        new LadderLength().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
    }

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        // 对单词做预处理出通用的状态
        Map<String, List<String>> dicMap = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1, len);
                if (dicMap.containsKey(key)) {
                    dicMap.get(key).add(word);
                } else {
                    List<String> trans = new ArrayList<>();
                    trans.add(word);
                    dicMap.put(key, trans);
                }
            }
        }

        // 用队列记录单词访问的层次深度
        Queue<Map<String, Integer>> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>(); // 原始单词和层深度
        map.put(beginWord, 1);
        queue.add(map);

        // 用一个字典记录被访问过的单词
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Map<String, Integer> curMap = queue.poll();
            String curWord = curMap.keySet().iterator().next();
            Integer curLevel = curMap.values().iterator().next();
            for (int i = 0; i < len; i++) {
                String key = curWord.substring(0, i) + "*" + curWord.substring(i + 1, len);
                List<String> curWordList = dicMap.get(key);
                if (curWordList == null) {
                    continue;
                }
                for (String word : curWordList) {
                    // 找到目标单词
                    if (word.equals(endWord)) {
                        return curLevel + 1;
                    }

                    // 否则要继续查找单词
                    // 添加到队列中
                    if (!visited.containsKey(word)) {
                        visited.put(word, true);
                        Map<String, Integer> newMap = new HashMap<>();
                        newMap.put(word, curLevel + 1);
                        queue.add(newMap);
                    }
                }
            }
        }

        return 0;
    }

    int minCount = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 对单词做预处理出通用的状态
        int len = beginWord.length();
        Map<String, List<String>> dicMap = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1, len);
                if (dicMap.containsKey(key)) {
                    dicMap.get(key).add(word);
                } else {
                    List<String> trans = new ArrayList<>();
                    trans.add(word);
                    dicMap.put(key, trans);
                }
            }
        }

        dfs(dicMap, new HashSet<>(), 0, beginWord, endWord);
        return minCount == Integer.MAX_VALUE ? 0 : minCount;
    }

    // dfs 深度优先搜索
    public void dfs(Map<String, List<String>> dicMap, HashSet<String> set, int count, String beginWord, String endWord) {
        // 终止条件, 找到转换的目标单词
        if (beginWord.equals(endWord)) {
            minCount = Math.min(count, minCount);
            return;
        }

        // 处理当前层
        for (int i = 0; i < beginWord.length(); i++) {
            String key = beginWord.substring(0, i) + "*" + beginWord.substring(i + 1);
            List<String> wordList = dicMap.get(key);
            if (wordList == null) {
                continue;
            }
            for (String word : wordList) {
                set.add(word);
                dfs(dicMap, set, count + 1, word, endWord);
                set.remove(word);
            }
        }
    }
}

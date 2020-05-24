package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 126. 单词接龙 II
 * <p>
 * https://leetcode-cn.com/problems/word-ladder-ii/
 */
public class FindLadders {

    public static void main(String[] args) {
        FindLadders f = new FindLadders();
        System.out.println(f.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    int minCount = 0;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        int len = beginWord.length();
        Map<String, List<String>> dictMap = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                if (dictMap.containsKey(key)) {
                    dictMap.get(key).add(word);
                } else {
                    List<String> words = new ArrayList<>();
                    words.add(word);
                    dictMap.put(key, words);
                }
            }
        }

        Queue<Map<String, Integer>> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        queue.add(map);

        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Map<String, Integer> curMap = queue.poll();
            String curWord = curMap.keySet().iterator().next();
            Integer curLevel = curMap.values().iterator().next();

            for (int i = 0; i < len; i++) {
                String curKey = curWord.substring(0, i) + "*" + curWord.substring(i + 1);
                List<String> curWordList = dictMap.get(curKey);
                if (curWordList == null) {
                    continue;
                }
                for (String word : curWordList) {
                    // 是否找到单词最短路径
                    if (word.equals(endWord)) {
                        minCount = curLevel + 1;
                        //
                    }

                    // 继续广度搜索
                    if (!visited.containsKey(word)) {
                        visited.put(word, true);
                        Map<String, Integer> newMap = new HashMap<>();
                        newMap.put(word, curLevel + 1);
                        queue.add(newMap);
                    }
                }
            }
        }

        return result;
    }
}

package com.github.zhgxun.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LadderLength3 {

    public static void main(String[] args) {
        System.out.println(new LadderLength3().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    private final Map<String, List<String>> dict = new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                if (dict.containsKey(key)) {
                    dict.get(key).add(word);
                } else {
                    List<String> trans = new ArrayList<>();
                    trans.add(word);
                    dict.put(key, trans);
                }
            }
        }

        Queue<Map<String, Integer>> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        queue.add(map);

        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Map<String, Integer> curMap = queue.poll();
            String curWord = curMap.keySet().iterator().next();
            Integer count = curMap.values().iterator().next();

            for (int i = 0; i < curWord.length(); i++) {
                String curKey = curWord.substring(0, i) + "*" + curWord.substring(i + 1);
                List<String> curWordList = dict.get(curKey);
                if (curWordList == null) continue;
                for (String word : curWordList) {
                    if (word.equals(endWord)) {
                        return count + 1;
                    }

                    if (visited.containsKey(word)) continue;

                    visited.put(word, true);

                    Map<String, Integer> tempMap = new HashMap<>();
                    tempMap.put(word, count + 1);
                    queue.add(tempMap);
                }
            }
        }

        return 0;
    }
}

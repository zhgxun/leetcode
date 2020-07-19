package com.github.zhgxun.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LadderLengthV2 {

    public int countSegments(String s) {
        if (s == null || s.length() == 0) return 0;

        int start = 0;
        while (start < s.length() && s.charAt(start) == ' ') start++;
        if (start == s.length() - 1) return 0;

        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') end--;
        if (end < 0) return 0;

        int count = 0;
        start++;
        while (start <= end) {
            if (s.charAt(start) == ' ' && s.charAt(start - 1) != ' ') count++;
            start++;
        }

        return count + 1;
    }

    public static void main(String[] args) {
        System.out.println(new LadderLengthV2().findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    private final Map<String, List<String>> dict = new HashMap<>();
    private final List<List<String>> result = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
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

        List<String> res = new ArrayList<>();
        res.add(beginWord);

        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Map<String, Integer> curMap = queue.poll();
            String curWord = curMap.keySet().iterator().next();
            Integer count = curMap.values().iterator().next();

            for (int i = 0; i < curWord.length(); i++) {
                String key = curWord.substring(0, i) + "*" + curWord.substring(i + 1);
                List<String> curWordList = dict.get(key);
                if (curWordList == null) continue;
                for (String word : curWordList) {
                    if (word.equals(endWord)) {
                        System.out.println("找到了");
                        System.out.println(res);
                        result.add(new ArrayList<>(res));
                        break;
                    }

                    if (visited.containsKey(word)) continue;
                    visited.put(word, true);

                    Map<String, Integer> temp = new HashMap<>();
                    temp.put(word, count + 1);
                    queue.add(temp);
                }
            }
        }

        return result;
    }
}

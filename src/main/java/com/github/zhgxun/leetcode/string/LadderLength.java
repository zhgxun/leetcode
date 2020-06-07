package com.github.zhgxun.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 127. 单词接龙
 * <p>
 * https://leetcode-cn.com/problems/word-ladder/
 */
public class LadderLength {

    public static void main(String[] args) {
        //System.out.println(new LadderLength().ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));
        System.out.println(new LadderLength().ladderLength("qa", "sq", Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye")));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);

        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);

        Set<String> visitedSet = new HashSet<>();

        int sum = 1;

        while (beginSet.size() > 0 && endSet.size() > 0) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> tempSet = new HashSet<>();
            for (String word : beginSet) {
                for (int i = 0; i < word.length(); i++) {
                    char[] chars = word.toCharArray();
                    char oldValue = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String target = String.valueOf(chars);
                        if (!wordSet.contains(target)) {
                            continue;
                        }
                        if (endSet.contains(target)) {
                            return sum + 1;
                        }
                        if (!visitedSet.contains(target)) {
                            visitedSet.add(target);
                            tempSet.add(target);
                        }
                    }
                    chars[i] = oldValue;
                }
            }

            beginSet = tempSet;
            sum++;
        }

        return 0;
    }

    private final HashMap<String, List<String>> dict = new HashMap<>();

    // 单向bfs
    public int ladderLengthV1(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                if (!dict.containsKey(key)) {
                    List<String> curWordList = new ArrayList<>();
                    curWordList.add(word);
                    dict.put(key, curWordList);
                } else {
                    dict.get(key).add(word);
                }
            }
        }

        // 队列记录单词和深度
        Queue<Map<String, Integer>> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        queue.add(map);

        // 记录被访问过的单词
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Map<String, Integer> curMap = queue.poll();
            String curWord = curMap.keySet().iterator().next();
            Integer curLevel = curMap.values().iterator().next();
            for (int i = 0; i < curWord.length(); i++) {
                String key = curWord.substring(0, i) + "*" + curWord.substring(i + 1);
                List<String> curWordList = dict.get(key);
                if (curWordList == null) {
                    continue;
                }

                // 均是符合变换一次的单词列表
                for (String word : curWordList) {
                    if (word.equals(endWord)) { // 找到最先变化得到的单词
                        return curLevel + 1;
                    }

                    // 未被处理过的单词继续搜索
                    if (!visited.containsKey(word)) {
                        visited.put(word, true);
                        Map<String, Integer> tempMap = new HashMap<>();
                        tempMap.put(word, curLevel + 1);
                        queue.add(tempMap);
                    }
                }
            }
        }

        return 0;
    }

    // 深度dfs最小基因变化可以但是单词接龙不可以
    private int minSum = Integer.MAX_VALUE;

    public int ladderLengthV2(String beginWord, String endWord, List<String> wordList) {
        dfs(new HashSet<>(), beginWord, endWord, wordList, 1);
        return minSum == Integer.MAX_VALUE ? 0 : minSum;
    }

    public void dfs(HashSet<String> wordSet, String beginWord, String endWord, List<String> wordList, int sum) {
        System.out.println(beginWord);
        // 终止条件找到目标单词, 记录最小变化次数
        if (beginWord.equals(endWord)) {
            minSum = Math.min(minSum, sum);
            return;
        }

        // 处理当前层
        // 变化一个单词去单词库中找这个单词
        for (String word : wordList) {
            int diffCount = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != beginWord.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1) { // 只能找相差一个字符的单词才符合题目一次变化一个字符的要求
                        break;
                    }
                }
            }

            // 找到单词
            if (diffCount == 1 && !wordSet.contains(word)) {
                wordSet.add(word);
                // 处理下一层
                dfs(wordSet, word, endWord, wordList, sum + 1);
                // 清理当前层
                wordSet.remove(word);
            }
        }
    }
}

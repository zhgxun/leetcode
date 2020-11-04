package com.github.zhgxun.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class Codec {

    public static void main(String[] args) {
        Codec codec = new Codec();
        String url = codec.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(codec.map);
        System.out.println(codec.decode(url));
    }

    private final Map<Integer, String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int code = longUrl.hashCode();
        map.put(code, longUrl);
        return "http://tinyurl.com/" + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}

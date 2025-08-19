package com.practice.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringUniqueChars {

    public static void main(String[] args) {
//        assert lengthOfLongestSubstring("dvdf") == 3 : "BAD";
//        assert lengthOfLongestSubstring("bbbbb") == 1 : "BAD";
//        assert lengthOfLongestSubstring("pwwkew") == 3 : "BAD";
//        assert lengthOfLongestSubstring("abcabcbb") == 3 : "BAD";
//        assert lengthOfLongestSubstring(" ") == 1 : "BAD";
//        assert lengthOfLongestSubstring("auv") == 3 : "BAD";
//        assert lengthOfLongestSubstring("cdd") == 2 : "BAD";
        assert lengthOfLongestSubstring("abba") == 2 : "BAD";

    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();
        Map<Character, Integer> chars = new HashMap<>();
        int maxCount = 0;
        for(int i = 0, l = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(chars.containsKey(c)){
                l = chars.get(c) + 1;
            }
            maxCount = Math.max(maxCount, i + 1 - l);
            chars.put(c, i);
        }
        return chars.size() == s.length() ? chars.size() : maxCount;
    }
}

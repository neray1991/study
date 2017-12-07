package stringRelated;


/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Dec 20, 2014
 Problem:    Substring with Concatenation of All Words
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/substring-with-concatenation-of-all-words/
 Notes:
 You are given a string, S, and a list of words, L, that are all of the same length. Find all 
 starting indices of substring(s) in S that is a concatenation of each word in L exactly once 
 and without any intervening characters.
 For example, given:
 S: "barfoothefoobarman"
 L: ["foo", "bar"]
 You should return the indices: [0,9].
 (order does not matter).
 Solution: 1. Brute + HashMap.
           2. Sliding Window + HashMap. from Sun Mian.
*/

import java.util.*;

public class SubstringwithConcatofAllWords {
	public static List<Integer> findSubstring(String S, String[] L) {
		List<Integer> res = new ArrayList<Integer>();
		if (S == null || S.length() == 0 || L.length == 0) return res;
		int M = S.length(), N = L.length, K = L[0].length();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String s : L) {
			if (map.containsKey(s)) {
				map.put(s, map.get(s)+1);
			} else {
				map.put(s, 1);
			}
		}
		for (int i = 0; i < M - N * K; i++) {
			HashMap<String, Integer> found = new HashMap<String, Integer>();
			int j = 0;
			for (; j < N; j++) {
				String tmp = S.substring(i + j * K, i + (j + 1) * K);
				if (!map.containsKey(tmp)) break;
				if (found.containsKey(tmp)) {
					if (map.get(tmp) <= found.get(tmp)) break;
					found.put(tmp, found.get(tmp) + 1);
				} else {
					found.put(tmp, 1);
				}
			}
			if (j == N) res.add(i);
		}
		return res;
	}
	
	public static List<Integer> findSubstring_2(String S, String[] L) {
		List<Integer> res = new ArrayList<Integer>();
		if (S == null || S.length() == 0 || L.length == 0) return res;
		int N = L.length, K = L[0].length();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String s : L) {
			if (map.containsKey(s)) {
				map.put(s, map.get(s)+1);
			} else {
				map.put(s, 1);
			}
		}
		for (int i = 0; i < K; i++) {
			HashMap<String, Integer> find = new HashMap<String, Integer>();
			for (int start = i, end = i, count = 0; end + K <= S.length(); end += K) {
				String tmp = S.substring(end, end + K);
				if (map.get(tmp) == null) {
					start = end + K;
					find.clear();
					count = 0;
					continue;
				}
				while (find.get(tmp) != null && find.get(tmp) >= map.get(tmp)) {
					String subStart = S.substring(start, start + K);
					find.put(subStart, find.get(subStart) - 1);
					start += K;
					--count;
				}
				find.put(tmp, (find.get(tmp) == null ? 0 : find.get(tmp)) + 1);
				count++;
				if (count < N) continue;
				res.add(start);
			}
		}
		return res;
	}
	
	public static void main(String args[]) {
		String S = "barfoofoothefoobarfoofooman";
		String[] L = {"foo", "bar", "foo"};
		System.out.println(SubstringwithConcatofAllWords.findSubstring(S, L));
		System.out.println(SubstringwithConcatofAllWords.findSubstring_2(S, L));

	}
}

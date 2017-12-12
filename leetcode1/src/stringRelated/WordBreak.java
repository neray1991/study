package stringRelated;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jan 26, 2015
Problem:    Word Break
Difficulty: Easy
Source:     http://oj.leetcode.com/problems/word-break/
Notes:
Given a string s and a dictionary of words dict, determine if s can be segmented into 
a space-separated sequence of one or more dictionary words.
For example, given
s = "leetcode",
dict = ["leet", "code"].
Return true because "leetcode" can be segmented as "leet code".
Solution: dp.
*/

import java.util.*;

public class WordBreak {
	public static boolean wordBreak(String s, Set<String> dict) {
		int n = s.length();
		boolean[] dp = new boolean[n+1];
		dp[n] = true;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = n; j > i; j--) {
				if (dict.contains(s.substring(i, j)) && dp [j]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[0];
	}
	
	/*
	 Author:     Andy, nkuwjg@gmail.com
	 Date:       Jan 29, 2015
	 Problem:    Word Break II
	 Difficulty: Easy
	 Source:     http://oj.leetcode.com/problems/word-break-ii/
	 Notes:
	 Given a string s and a dictionary of words dict, add spaces in s to 
	 construct a sentence where each word is a valid dictionary word.
	 Return all such possible sentences.
	 For example, given
	 s = "catsanddog",
	 dict = ["cat", "cats", "and", "sand", "dog"].
	 A solution is ["cats and dog", "cat sand dog"].
	 Solution: check before constructing the sentences.
	*/
	
	public static List<String> wordBreakII(String s, Set<String> dict) {
		int n = s.length();
		boolean[] dp = new boolean[n+1];
		dp[n] = true;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = n; j > i; j--) {
				if (dict.contains(s.substring(i, j)) && dp[j]) {
					dp[i] = true;
					break;
				}
			}
		}
		if (dp[0] == false) return null;
		List<String> res = new ArrayList<String>();
		System.out.println("****");
		wordBreakIIRe(s, dict, "", 0, res);
		return res;
	}
	
	public static void wordBreakIIRe(String s, Set<String> dict, String path, int start, List<String> res) {
		if (start == s.length()) {
			res.add(path);
			return;
		}
		if (path.length() != 0) path = path + " ";
		for (int i = start; i < s.length(); i++) {
			String word = s.substring(start, i + 1);
			if (dict.contains(word) == false) continue;
			wordBreakIIRe(s, dict, path + word, i + 1, res);
		}
	}
	
	public static void main(String args[]) {
		Set<String> dict = new HashSet<String>();
		dict.add("leet");
		dict.add("code");
		dict.add("cat");
		dict.add("cats");
		dict.add("and");
		dict.add("sand");
		dict.add("dog");
		System.out.println(WordBreak.wordBreak("leetleetcode", dict));
		System.out.println(WordBreak.wordBreakII("catsanddog", dict));
	}
}

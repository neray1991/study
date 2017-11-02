package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Longest Common Prefix
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/longest-common-prefix/
Notes:
Write a function to find the longest common prefix string amongst an array of strings.
Solution: ...
*/

public class LongestCommonPrefix {
	public static String longestCommonPrefix(String[] str) {
		int n = str.length;
		if (n == 0) return null;
		if (n == 1) return str[0];
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < str[0].length(); i++) {
			int j = 1;
			for (; j < n - 1;) {
				if (i >= str[j].length()) break;
				if (str[j].charAt(i) == str[0].charAt(i)) j++;
				else break;
			}
			if (j >= n - 1) res.append(str[0].charAt(i));
		}
		return res.toString();
	}
	
	public static String longestCommonPrefix_0(String[] str) {
		int n = str.length;
		if (n == 0) return new String("");
		for (int i = 0; i < str[0].length(); i++)
			for (int j = 0; j < str.length; j++)
				if (i >= str[j].length() || str[j].charAt(i) != str[0].charAt(i)) return str[0].substring(0, i);
		return str[0];
	}
	
	public static void main(String args[]) {
		String[] str = {"abcab", "ababc", "abaab", "abbca", "abcba"};
		System.out.println(LongestCommonPrefix.longestCommonPrefix(str));
		System.out.println(LongestCommonPrefix.longestCommonPrefix_0(str));
	}
}

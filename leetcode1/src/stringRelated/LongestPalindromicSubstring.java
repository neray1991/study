package stringRelated;

import java.util.Arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 13, 2014
Problem:    Longest Palindromic Substring
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/longest-palindromic-substring/
Notes:
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
Solution: 1. Time O(n^2), Space O(n^2)
          2. Time O(n^2), Space O(n)
          3. Time O(n^2), Space O(1) (actually much more efficient than 1 & 2)
          4. Time O(n), Space O(n) (Manacher's Algorithm)
          5. Time O(n), Smaller Space than solution 4. (Manacher's Algorithm)
*/


public class LongestPalindromicSubstring {
	public static String longestPalindromicSubString(String str) {
		int n = str.length(), len = 0;
		boolean identical = true;
		String s1 = "";
		String s2 = "";
		String res = "";
		for (int i = 0; i < n; i++) {
			if (s1.length() == 0) {
				s1 = "" + str.charAt(i);
				continue;
			}
			if (s1.charAt(s1.length()-1) != str.charAt(i)) {
				if (identical) {
					if (s2.length() == 0 || s2.charAt(0) == str.charAt(i)) {
						s2 = s2 + str.charAt(i);
						continue;
					}
				}
				if (len < s2.length()) {
					len = s2.length();
					res = s2;
				}
				if (identical == false) {
					s1 = s1 + s2 + str.charAt(i);
					s2 = "";
				} else {
					s1 = s1 + s2;
					s2 = "" + str.charAt(i);
				}
				identical = true;
			} else {
				s2 = str.charAt(i) + s2 + str.charAt(i);
				if (s2.charAt(0) != s2.charAt(1)) identical = false;
				s1 = s1.substring(0, s1.length()-1);
			}
		}
		if (s2.length() > len)
			return s2;
		return res;
	}
	
	public static String longestPalindrome_1(String s) {
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		int idx = 0, maxLen = 0;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i + k < n; i++) {
				if (k == 0 || k == 1) dp[i][i+k] = (s.charAt(i) == s.charAt(i+k));
				else dp[i][i+k] = (s.charAt(i) == s.charAt(i+k)) ? dp[i+1][i+k-1] : false;
				if (dp[i][i+k] == true && (k+1) > maxLen) {
					idx = i; maxLen = k + 1;
				}
			}
		}
		return s.substring(idx, idx + maxLen);
	}
	
	public static String longestPalindrome_3(String s) {
		int n = s.length();
		int idx = 0, maxLen = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= 1; j++) {
				boolean isP = true;
				for (int k = 0; i - k >= 0 && i + j + k < n && isP; ++k) {
					isP = (s.charAt(i - k) == s.charAt(i + j + k));
					if (isP && (j + 1 + k * 2) > maxLen) {
						idx = i - k; maxLen = j + 1 + k * 2;
					}
				}
			}
		}
		return s.substring(idx, idx + maxLen);
	}
	
	public static String longestPalindrome_5(String s) {
		int n = s.length();
		int idx = 0, maxLen = 0;
		int mx = 0, id = 0;
		int[] p = new int[2*n+1];
		Arrays.fill(p, 0);
		for (int i = 0; i < 2*n+1; ++i) {
			p[i] = (mx > i) ? Math.min(p[2*id-i], mx - i) : 0; //2*id-i = id-(i-id), try to find the symetrical position of i, instead of count from the beginning. 
			int left = i - 1 - p[i], right = i + 1 + p[i];
			while (left >= 0 && right <= 2*n) {
				if (left % 2 == 0 || s.charAt(left/2) == s.charAt(right/2)) {
					++p[i];
				} else break;
				--left;
				++right;
			}
			if (i + p[i] > mx) {
				id = i; mx = i + p[i];
			}
			if (p[i] > maxLen) {
				idx = i; maxLen = p[i];
			}
		}
		System.out.println(Arrays.toString(p));
		idx = (idx - maxLen) / 2;
		return s.substring(idx, idx + maxLen);
	}
	
	public static void main(String args[]) {
		System.out.println(LongestPalindromicSubstring.longestPalindromicSubString("abcddcbbbbbdbbbb"));
		System.out.println(LongestPalindromicSubstring.longestPalindrome_1("abcddcbbbbbdbbbb"));
		System.out.println(LongestPalindromicSubstring.longestPalindrome_3("abcddcbbbbbdbbbb"));
		System.out.println(LongestPalindromicSubstring.longestPalindrome_5("abcddcbbbbbdbbbb"));
	}
}

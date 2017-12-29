package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Wildcard Matching
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/wildcard-matching/
Notes:
Implement wildcard pattern matching with support for '?' and '*'.
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).
The function prototype should be:
bool isMatch(const char *s, const char *p)
Some examples:
isMatch("aa","a") ? false
isMatch("aa","aa") ? true
isMatch("aaa","aa") ? false
isMatch("aa", "*") ? true
isMatch("aa", "a*") ? true
isMatch("ab", "?*") ? true
isMatch("aab", "c*a*b") ? false
Solution: 1. if s[i]  == p[j] || p[j] == '?', ++i and ++j.
               ii, jj, record the positon of '*' in s and p, ++j and go on.
               if not match, go back to star, i = ++ii;
*/


public class WildcardMatch {
	public static boolean isMatch(String s, String p) {
		int ii = -1, jj = -1, i = 0, j = 0;
		while (i < s.length()) {
			if (j < p.length() && p.charAt(j) == '*') {
				while (j < p.length() && p.charAt(j) == '*') j++;
				if (j == p.length()) return true;
				ii = i;
				jj = j;
			}
			if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
				i++; j++;
			} else {
				if (ii == -1)  return false; //Once * appears in p before we get to this point, we'll have to check until the last char of s.
				ii++; //ii++ here means one char at ii is used to match * at String p.
				i = ii; //Then we fall back.
				j = jj; //Fall back to the char right befind *
			}
		}
		while (j < p.length() && p.charAt(j) == '*') j++;
		return i == s.length() && j == p.length();
	}
	
	public static void main(String args[]) {
		System.out.println(WildcardMatch.isMatch("abcaa", "a*bb*"));
	}
}

package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Oct 26, 2014
Problem:    Regular Expression Matching
Difficulty: Hard
Source:     https://oj.leetcode.com/problems/regular-expression-matching/
Notes:
Implement regular expression matching with support for '.' and '*'.
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).
The function prototype should be:
bool isMatch(const char *s, const char *p)
Some examples:
isMatch("aa","a") ? false
isMatch("aa","aa") ? true
isMatch("aaa","aa") ? false
isMatch("aa", "a*") ? true
isMatch("aa", ".*") ? true
isMatch("ab", ".*") ? true
isMatch("aab", "c*a*b") ? true
Solution: 1. Recursion.
          2. DP.
*/

public class RegularExpression {
	public static boolean isMatch(String s, String p) {
		if (p.length() == 0) return s.length() == 0;
		if (p.length() == 1) {
			if (s.length() != 1)
				return false;
			return (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
		}
		if (s.length() != 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))) {
			if (p.charAt(1) == '*') {
				return (isMatch(s.substring(1), p) || isMatch(s, p.substring(2)));
			}
			return (isMatch(s.substring(1), p.substring(1)));
		}
		return p.charAt(1) == '*' && isMatch(s, p.substring(2));
	}
	
	public static boolean isMatch_1 (String s, String p) {
		if (p.length() == 0) return s.length() == 0;
		boolean[][] dp = new boolean[s.length()+1][p.length()+1];
		dp[0][0] = true;
		for (int i = 2; i <= p.length(); i++)
			if (p.charAt(i-1) == '*')
				dp[0][i] = dp[0][i-2];
		for (int i = 1; i <= s.length(); i++)
			for (int j = 1; j <= p.length(); j++) {
				char stmp = s.charAt(i-1);
				char ptmp = p.charAt(j-1);
				if (ptmp != '*') dp[i][j] = dp[i-1][j-1] && (stmp == ptmp || ptmp == '.');
				else {
					dp[i][j] = dp[i][j-2];
					if (stmp == p.charAt(j-2))
						dp[i][j] |= dp[i][j-1];
				}
			}
		return dp[s.length()][p.length()];
	}
	public static void main(String args[]) {
		String s = "acb";
		String p = "a*cb";
		System.out.println(RegularExpression.isMatch(s, p));
		System.out.println(RegularExpression.isMatch_1(s, p));
	}
}

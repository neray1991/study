package stringRelated;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jan 20, 2015
Problem:    Palindrome Partitioning
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/palindrome-partitioning/
Notes:
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
For example, given s = "aab",
Return
[
 ["aa","b"],
 ["a","a","b"]
]
Solution: ...
*/

import java.util.*;

public class PalindromePartition {
	public List<List<String>> partition(String s) {
		int n = s.length();
		List<List<String>> res = new ArrayList<List<String>>();
		boolean[][] dp = new boolean[n][n];
		for (int i = n-1; i >= 0; i--)
			for (int j = i; j < n; j++)
				dp[i][j] = (s.charAt(i) == s.charAt(j) && ((j < i + 2) || dp[i+1][j-1]));//j always >= i here
		ArrayList<String> path = new ArrayList<String>();
		dfs(s, dp, 0, path, res);
		return res;
	}
	
	public void dfs(String s, boolean[][] dp, int start, ArrayList<String> path, List<List<String>> res) {
		if (s.length() == start) {
			res.add(new ArrayList<String>(path));
		}
		for (int i = start; i < s.length(); i++) {
			if (dp[start][i] == false) continue;
			path.add(s.substring(start, i + 1));
			dfs(s, dp, i+1, path, res);
			path.remove(path.size() - 1);
		}
	}
	
	public int minCut(String s) {
		int n = s.length();
		boolean isP[][] = new boolean[n][n];
		int dp[] = new int[n+1];
		dp[n] = -1;
		for (int i = n-1; i >= 0; i--) {
			dp[i] = n - 1 - i;
			for (int j = i; j < n; j++) {
				if (s.charAt(i) == s.charAt(j) && (j < i+2 || isP[i+1][j-1])) { 
					isP[i][j] = true;
					dp[i] = Math.min(dp[i], dp[j+1] + 1);
				}
			}
		}
		return dp[0];
	}
	
	public static void main(String args[]) {
		String s = "aabbcbbd";
		System.out.println(new PalindromePartition().partition(s));
		System.out.println(new PalindromePartition().minCut(s));
	}
}

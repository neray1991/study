package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Jan 3, 2015
Problem:    Interleaving String
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/interleaving-string/
Notes:
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
For example,
Given:
s1 = "aabcc",
s2 = "dbbca",
When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
Solution: 1. dp. O(MN) time & space. 'dp[i][j] == true' means that there is at least one way to construct 
             the string s3[0...i+j-1] by interleaving s1[0...j-1] and s2[0...i-1].
*/

public class InterleavingString {
	/*
	 * Similar to the algorithm in stringRelated.EditDistance.java
	 */
	public static boolean isInterleav(String s1, String s2, String s3) {
		int m = s1.length();
		int n = s2.length();
		int t = s3.length();
		if (t == 0 || t != m + n) return false;
		if (m == 0) return s2.compareTo(s3) == 0;
		if (n == 0) return s1.compareTo(s3) == 0;
		boolean[][] dp = new boolean[m+1][n+1];
		dp[0][0] = true;
		for (int i = 1; i <= m; i++)
			dp[i][0] = (s1.charAt(i-1) == s3.charAt(i-1)) && dp[i-1][0];
		for (int i = 1; i <= n; i++)
			dp[0][i] = (s2.charAt(i-1) == s3.charAt(i-1)) && dp[0][i-1];
		for (int i = 1; i <= m; i++)
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i-1) == s3.charAt(i+j-1)) //Just like above, s1.charAt(i-1): it should be i-1 here.
					dp[i][j] |= dp[i-1][j];
				if (s2.charAt(j-1) == s3.charAt(i+j-1))
					dp[i][j] |= dp[i][j-1];
			}
		return dp[m][n];
	}
	
	public static void main(String args[]) {
		System.out.println(InterleavingString.isInterleav("aabcc", "dbbca", "aadbbbaccc"));
	}
}

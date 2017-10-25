package stringRelated;
/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Oct 07, 2014
 Problem:    Distinct Subsequences
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/distinct-subsequences/
 Notes:
 Given a string S and a string T, count the number of distinct subsequences of T in S.
 A subsequence of a string is a new string which is formed from the original string by deleting
 some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 Here is an example:
 S = "rabbbit", T = "rabbit"
 Return 3.
 Solution: dp.
 */

/*Record the first char in T that appears in S before second char.
 * When second char appears, set dp[2]=dp[1], when second char appears
 * again, dp[2]+=dp[1], so on...
 */

import java.util.Arrays;

public class NumDistinct {
	public static int numDistinct (String S, String T) {
		int m = S.length();
		int n = T.length();
		int[] dp = new int[n + 1];
		Arrays.fill(dp, 0);
		dp[0] = 1;
		for (int i = 1; i <= m; ++i)
			for (int j = n; j >= 1; j--) {
				dp[j] = dp[j] + (S.charAt(i - 1) == T.charAt(j - 1) ? dp[j - 1] : 0);
				System.out.println("dp"+j+"="+dp[j]);
			}
		return dp[n];
	}
	
	public static void main(String args[]) {
		String S = "121211111";
		String T = "121";
		System.out.println(NumDistinct.numDistinct(S, T));
	}
}

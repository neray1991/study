package arrays2D;

import java.util.Arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 26, 2014
Problem:    Minimum Path Sum
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/minimum-path-sum/
Notes:
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right 
which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.
Solution: Dynamic Programming. Space O(N).
*/

public class MinPathSum {
	public static int minPathSum(int[][] input) {
		int m = input.length;
		int n = input[0].length;
		int[][] dp = new int[m][n];
		dp[0][0] = input[0][0];
		for (int i = 1; i < m; i++)
			dp[i][0] = dp[i-1][0] + input[i][0];
		for (int i = 1; i < n; i++)
			dp[0][i] = dp[0][i-1] + input[0][i];
		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++)
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + input[i][j];
		return dp[m-1][n-1];
	}
	
	public static int minPathSum_0(int[][] input) {
		if (input.length == 0) return Integer.MIN_VALUE;
		int m = input.length;
		int n = input[0].length;
		int[] dp = new int[n];
		dp[0] = input[0][0];
		for (int i = 1; i < n; i++)
			dp[i] = dp[i-1] + input[0][i];
		for (int i = 1; i < m; i++) {
			dp[0] = dp[0] + input[i][0];
			for (int j = 1; j < n; j++)
				dp[j] = Math.min(dp[j], dp[j-1]) + input[i][j];
		}
		return dp[n-1];
	}
	
	public static void main(String args[]) {
		int row = 5, col = 5;
		int[][] input = new int[row][col]; 
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				input[i][j] = dataStructures.RandomStruct.getRandomInt(20);
			System.out.println(Arrays.toString(input[i]));
		}
		System.out.println(MinPathSum.minPathSum(input));
		System.out.println(MinPathSum.minPathSum_0(input));
	}
}

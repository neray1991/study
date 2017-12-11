package arrays2D;


/*
 Author:     King, wangjingui@outlook.com
 Date:       Oct 9, 2014
 Problem:    Unique Paths
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/unique-paths/
 Notes:
 A robot is located at the top-left corner of a m x n grid.
 The robot can only move either down or right at any point in time. The robot is trying to reach 
 the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 How many possible unique paths are there?
 Solution: 
 1. Use formula C(x,t) = t!/(x!*(t-x)!) (x should be large for calculation).
 2. Dynamic programming. UP(i,j) = UP(i-1,j) + UP(i,j-1).
 */

/*
Author:     King, higuige@gmail.com
Date:       Dec 25, 2014
Problem:    Unique Paths II
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/unique-paths-ii/
Notes:
Follow up for "Unique Paths":
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.
For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.
[
 [0,0,0],
 [0,1,0],
 [0,0,0]
]
The total number of unique paths is 2.
Note: m and n will be at most 100.
Solution: Dynamic programming.
*/

public class UniquePaths {
	public static int uniquePath(int[][] A) {
		if (A.length == 0 || A[0].length == 0 || A[0][0] != 0) return 0;
		int m = A.length, n = A[0].length;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			if (A[i][0] != 0 || (i > 0 && dp[i-1][0] == 0)) 
				dp[i][0] = 0;
			else
				dp[i][0] = 1;
			for (int j = 1; j < n; j++) {
				if (i == 0) {
					if (A[0][j] != 0 || (j > 0 && dp[0][j-1] == 0))
						dp[0][j] = 0;
					else
						dp[0][j] = 1;
					continue;
				}
				if (A[i][j] != 0) 
					dp[i][j] = 0;
				else
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
	}
	
	public static int uniquePath_0(int[][] A) {
		if (A.length == 0 || A[0].length == 0 || A[0][0] != 0) return 0;
		int m = A.length, n = A[0].length;
		int[] dp = new int[n];
		if(A[m-1][n-1] == 1) return 0;
		dp[0] = 1;
		for (int i = 0; i < m; ++i) {
			dp[0] = A[i][0] == 1 ? 0 : dp[0];
			for (int j = 1; j < n; j++) {
				dp[j] = A[i][j] == 1 ? 0 : dp[j] + dp[j-1]; //Reuse dp[], dp[j] is the path to the above row.
			}
		}
		return dp[n-1];
	}
	
	public static void main(String args[]) {
		int[][] A = {{0,0,1},
					{0,0,0},
					{0,0,0}};
		System.out.println(UniquePaths.uniquePath(A));
		System.out.println(UniquePaths.uniquePath_0(A));
	}
}

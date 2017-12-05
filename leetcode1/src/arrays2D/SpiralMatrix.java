package arrays2D;

import java.util.Arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 25, 2014
Problem:    Spiral Matrix
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/spiral-matrix/
Notes:
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
For example,
Given the following matrix:
[
[ 1, 2, 3 ],
[ 4, 5, 6 ],
[ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
Solution: ...
*/

public class SpiralMatrix {
	public static int[] spiralMatrix(int[][] num) {
		if (num.length == 0 || num[0].length == 0)
			return null;
		int m = num.length, n = num[0].length;
		int level = Math.min(m, n) / 2 + Math.min(m, n) % 2;
		int[] res = new int[m*n];
		int idx = 0;
		for (int k = 0; k <= level; k++) {
			for (int i = k; i < n - k; i++) {
				//System.out.println("idx="+idx+"i="+i);
				res[idx++] = num[k][i];
				if (idx >= res.length) return res; //Be careful, we may need to return here.
			}
			for (int i = k + 1; i < m - k; i++) {
				res[idx++] = num[i][n-k-1];
				if (idx >= res.length) return res; //And here. Yeah, when m > n;
			}
			for (int i = n - k - 2; i >= k; i--) {
				res[idx++] = num[m-k-1][i];
				if (idx >= res.length) return res; //And here
			}
			for (int i = m - k - 2; i > k; i--) {
				res[idx++]= num[i][k];
				if (idx >= res.length) return res; //And here
			}
		}
		return res;
	}
	
	/*
	 Author:     King, wangjingui@outlook.com
	 Date:       Dec 25, 2014
	 Problem:    Spiral Matrix II
	 Difficulty: Easy
	 Source:     https://oj.leetcode.com/problems/spiral-matrix-ii/
	 Notes:
	 Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
	 For example,
	 Given n = 3,
	 You should return the following matrix:
	 [
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	 ]
	 Solution: ...
	 */
	
	public static int[][] spiralMatrixII (int n) {
		if (n <= 0) return null;
		if (n == 1) {  
			int[][] res = {{1}};
			return res;
		}
		int[][] res = new int[n][n];
		int level = n / 2 + n % 2;
		int num = 1;
		for (int k = 0; k <= level; k++) {
			for (int i = k; i < n - k; i++) {
				res[k][i] = num++;
				if (num > n * n) return res; //num > n * n is right, not num >= n * n
			}
			for (int i = k + 1; i < n - k; i++) {
				res[i][n-k-1] = num++;
				if (num > n * n) return res; 
			}
			for (int i = n - k - 2; i >= k; i--) {
				res[n-k-1][i] = num ++;
				if (num > n * n) return res;
			}
			for (int i = n - k - 2; i > k; i--) {
				res[i][k] = num++;
				if (num > n * n) return res; 
			}
		}
		return res;
	}
	
	public static void main(String args[]) {
		int[][] num = {{1,2,3,4},
				   //{5,6,7,8},
				  // {9,10,11,12},
				   {13,14,15,16}};
		int[] res = SpiralMatrix.spiralMatrix(num);
		System.out.println(Arrays.toString(res));
		System.out.println("*******");
		int[][] res2 = SpiralMatrix.spiralMatrixII(5);
		for (int i = 0; i < 4; i++)
			System.out.println(Arrays.toString(res2[i]));
	}
}

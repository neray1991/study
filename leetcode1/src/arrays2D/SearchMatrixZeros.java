package arrays2D;

import java.util.Arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 25, 2014
Problem:    Set Matrix Zeroes
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/set-matrix-zeroes/
Notes:
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

Solution: Use first row and column as auxiliary spaces instead of newly allocating ones.
*/

public class SearchMatrixZeros {
	public static void searchMatrixZeros(int[][] num) {
		if (num.length == 0 || num[0].length == 0) return;
		int m = num.length, n = num[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (num[0][j] == 0 || num[i][0] == 0) {
					num[i][j] = 0;
				} else if (num[i][j] == 0) {
					for (int k = 0; k < j; k++)
						num[i][k] = 0;
					for (int k = 0; k < i; k++)
						num[k][j] = 0;
				}
			}
		}
		return;
	}
	
	public static void main(String args[]) {
		int[][] num = {{1,2,3,4},
						{5,10,7,8},
						{9,0,11,12},
						{13,0,0,16}};
		for (int i = 0; i < num.length; i++)
			System.out.println(Arrays.toString(num[i]));
		System.out.println("***");
		SearchMatrixZeros.searchMatrixZeros(num);
		for (int i = 0; i < num.length; i++)
			System.out.println(Arrays.toString(num[i]));
	}
}

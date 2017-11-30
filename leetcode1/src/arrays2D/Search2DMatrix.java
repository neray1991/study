package arrays2D;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jan 16, 2015
Problem:    Search a 2D Matrix
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/search-a-2d-matrix/
Notes:
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,
Consider the following matrix:
[
   [1,   3,  5,  7],
   [10, 11, 16, 20],
   [23, 30, 34, 50]
]
Given target = 3, return true.
Solution: Binary-search.
*/

//import arrays.SearchInsertPosition; //We can use this solution.

public class Search2DMatrix {
	public static boolean search2DMatrix(int[][] num, int k) {
		int m = num.length; 
		if (m == 0) return false;
		int n = num[0].length;
		if (n == 0) return false; //Need this line.
		int start = 0, end = m - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (k == num[mid][0]) return true;
			if (k > num[mid][0]) start = mid + 1;
			else end = mid - 1;
		}
		int row = start - 1;		//Careful here, different from Insert function! We have to minus one and do a if.
		if (row < 0) return false;
		start = 0; end = n - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (k == num[row][mid]) return true;
			if (k > num[row][mid]) start = mid + 1;
			else end = mid - 1;
		}
		System.out.println("row="+row+", start="+start);
		return (num[row][start] == k);
	}
	
	public static boolean search2DMatrix_0(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0) return false;
		int N = matrix.length, M = matrix[0].length;
		int left = 0, right = M * N - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			int row = mid / M, col = mid % M;
			if (matrix[row][col] == target) return true;
			if (matrix[row][col] < target) left = mid + 1;
			else right = mid - 1;
		}
		return false;
	}
	/*
	 * Both methods got a O(log(MN)) time complexity.
	 */
	
	public static void main(String args[]) {
		int[][] num = {
		               {1,   3,  5,  7},
		               {10, 11, 16, 20},
		               {23, 30, 34, 50}
						};
		System.out.println(Search2DMatrix.search2DMatrix(num, 34));
		System.out.println(Search2DMatrix.search2DMatrix_0(num, 34));
	}
}

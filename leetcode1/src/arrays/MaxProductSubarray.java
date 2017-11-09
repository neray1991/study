package arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Oct 06, 2014
Problem:    Maximum Product Subarray
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/maximum-product-subarray/
Notes:
Find the contiguous subarray within an array (containing at least one number) which has the largest product.
For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

public class MaxProductSubarray {
	public static int maxProductSubarray(int[] input) {
		int N = input.length;
		if (N == 0) return 0;
		int maxVal = input[0], minVal = input[0], res = input[0];
		for (int i = 1; i < N; i++) {
			int tmpVal = maxVal;//This needs to be used when calculating minVal.
			maxVal = Math.max(Math.max(maxVal * input[i], minVal * input[i]), input[i]);
			minVal = Math.min(Math.min(tmpVal * input[i], minVal * input[i]), input[i]);
			res = Math.max(res, maxVal);
		}
		return res;
	}
	
	public static void main(String args[]) {
		int[] input = {2,3,4,-2,3,0,1,3,4,5,-3,-2};
		System.out.println(MaxProductSubarray.maxProductSubarray(input));
	}
}

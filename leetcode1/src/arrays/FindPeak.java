package arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 06, 2014
Problem:    Find Peak Element
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/find-peak-element/
Notes:
A peak element is an element that is greater than its neighbors.
Given an input array where num[i] ¡Ù num[i+1], find a peak element and return its index.
You may imagine that num[-1] = num[n] = -¡Þ.
For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
Find the peak element.
*/

public class FindPeak {
	/*Only have one peak!*/
	public static int findPeak(int[] input) {
		int left = 0, right = input.length - 1, mid = -1;
		while (left < right ) {
			mid = (left + right) / 2;
			if ((mid == 0 || input[mid-1] <= input[mid]) && (mid == input.length - 1 || input[mid] >= input[mid+1]))
				return mid;
			if (mid > 0 && input[mid-1] > input[mid])
				right = mid - 1;
			else if (input[mid+1] > input[mid])
				left = mid + 1;
		}
		return mid;
	}
	
	public static void main(String args[]) {
		int[] input = {0,1,2,3,4,3,2,1,-1};
		System.out.println(FindPeak.findPeak(input));
	}
}

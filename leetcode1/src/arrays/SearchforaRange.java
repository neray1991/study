package arrays;

import java.util.Arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Search for a Range
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/search-for-a-range/
Notes:
Given a sorted array of integers, find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].
For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
Solution: It takes O(lgN) to find both the lower-bound and upper-bound.
*/

public class SearchforaRange {
	public static int[] searchforaRange(int[] num, int k) {
		int n = num.length;
		int[] res = {-1, -1};
		if (n == 0) return res;
		res[0] = getLowerBound(num, k);
		res[1] = getUpperBound(num, k);
		if (res[1] < res[0]) {
			res[0] = -1;
			res[1] = -1;
		}
		return res;
	}
	
	/*
	 * If start and end equals here, we haven't found the right boundary.
	 */
	
	public static int getLowerBound(int[] num, int k) {
		int start = 0, end = num.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (num[mid] >= k) end = mid - 1;//num[mid] >= k and start = mid + 1, then when start > end, start will be the first element that >= k.
			else start = mid + 1;
		}
		return start;
	}
	public static int getUpperBound(int[] num, int k) {
		int start = 0, end = num.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (num[mid] <= k) start = mid + 1;
			else end = mid - 1;
		}
		return end;
	}
	
	public static void main(String args[]) {
		int[] num = {5,7,7,8,8,10};
		System.out.println(Arrays.toString(SearchforaRange.searchforaRange(num, 10)));
	}
}

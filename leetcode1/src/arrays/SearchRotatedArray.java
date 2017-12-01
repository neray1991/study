package arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Search in Rotated Sorted Array
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/search-in-rotated-sorted-array/
Notes:
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Solution: Binary search. O(lgn) eg. [4 5 6] -7- 8 1 2, 5 6 0 -1- [2 3 4]
*/

public class SearchRotatedArray {
	public static int searchRotateArray(int[] num, int k) {
		int n = num.length;
		if (n == 0) return Integer.MIN_VALUE;
		int start = 0, end = n - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (num[mid] == k) return mid;
			/*
			if (num[mid] < k) {
				if (num[mid] < num[end] && num[end] < num[start])
					end = mid - 1;
				else start = mid + 1;
			} else {
				if (num[mid] > num[start] && num[end] < num[start])
					start = mid + 1;
				else end = mid - 1;
			}
			
			if (num[start] <= num[end]) {
				if (num[mid] < k)
					start = mid + 1;
				else
					end = mid - 1;
			} else {
				if (num[mid] >= num[start] && k <= num[end])
					start = mid + 1;
				if (num[mid] <= num[end] && k >= num[start])
					end = mid - 1;
				if (num[mid] < k)
					start = mid + 1;
				else
					end = mid - 1;
			}*/
		}
		return -1;
	}
	
	public static int search_0(int[] num, int k) {
		int start = 0, end = num.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (num[mid] == k) return mid;
			if (num[start] <= num[mid]) {//This has 2 end move and 3 start move cases
				if (num[start] <= k && k < num[mid])
					end = mid - 1;
				else 
					start = mid + 1;
			} else {						//this has 1 start move and 2 end move cases
				if (num[mid] < k && k <= num[end])
					start = mid + 1;
				else
					end = mid - 1;
			}
		}
		return -1;
	}
	
	/*
	 Author:     King, higuige@gmail.com
	 Date:       Nov 18, 2014
	 Problem:    Search in Rotated Sorted Array II
	 Difficulty: Medium
	 Source:     https://oj.leetcode.com/problems/search-in-rotated-sorted-array-ii/
	 Notes:
	 Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 What if duplicates are allowed?
	 Would this affect the run-time complexity? How and why?
	 Write a function to determine if a given target is in the array.
	 Solution: Sequence search. O(n)
	           Since there are duplicates, it's hard to decide which branch to go if binary-search is deployed.
	 */
	
	public static boolean search_1(int[] num, int k) {
		int start = 0, end = num.length - 1;
		while (start <= end) {
			int mid = (start + end ) / 2;
			if (num[mid] == k) return true;
			if (num[start] < num[mid]) { //This has 2 end move and 3 start move cases
				if (k >= num[start] && k < num[mid])
					end = mid - 1;
				else
					start = mid + 1;
			} else if (num[start] > num[mid]) {
				if (k > num[mid] && k <= num[end]) //this has 1 start move and 2 end move cases
					start = mid + 1;
				else
					end = mid - 1;
			} else {
				if (num[start] == num[end]) {
					start++;
					end--;
				} else { //This is the key, num[start] = num[mid] but != num[end], we just start++;
					start = mid + 1;
				}
			}
		}
		return false;
	}
	
	public static void main(String args[]) {
		int[] num = {4,5,6,6,6,7,7,9,1,2,3,4,4,4,4,4};
	//	System.out.println(SearchRotatedArray.searchRotateArray(num, 3));//They got the only right way.
		//System.out.println(SearchRotatedArray.search_0(num, 3));
		System.out.println(SearchRotatedArray.search_1(num, 8));
	}
}

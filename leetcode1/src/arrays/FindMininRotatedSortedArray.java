package arrays;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Oct 22, 2014
Problem:    Find Minimum in Rotated Sorted Array
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/
Notes:
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
*/

public class FindMininRotatedSortedArray {
/*	You may assume no duplicate exists in the array.*/
	public static int findMin (int[] input) {
		int n = input.length;
		if (n == 0) return 0;
		int left = 0, right = n -1, mid = (left + right) / 2;
		while(input[left] > input[right]) {
			if (input[mid] <= input[right])
				right = mid;
			else 
				left = mid + 1;
			mid = (left + right) / 2;
		}
		return input[left];
	}
	
	/*
	 * The array may contain duplicates.
	 */
	public static int findMin_2 (int[] input) {
		int n = input.length;
		if (n == 0) return 0;
		int left = 0, right = n - 1, mid = (left + right) / 2;
		while(input[left] >= input[right] && left < right) {
			if (input[mid] > input[left]) //Don't need to consider equal occasion, if will be covered in else.
				left = mid + 1;
			else if (input[mid] < input[right])
				right = mid;
			else
				left++;//Move left because we are gonna return left. Loop will over when left == right.
		}
		return input[left];
	}
	
	public static void main(String args[]) {
		//int[] input = {0,1,2,3,4,5,6,-1};
		int[] input = {0,1,-1,-1,-1,0,0};
		System.out.println(FindMininRotatedSortedArray.findMin_2(input));
	}
}

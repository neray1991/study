package arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 12, 2014
Problem:    Remove Duplicates from Sorted Array
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array/
Notes:
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.
For example,
Given input array A = [1,1,2],
Your function should return length = 2, and A is now [1,2].
Solution: Update 7/16/2013: Let j start from 0 for better understanding.
*/

public class RemoveDuplicates {
	public static int removeDuplicates(int[] A) {
		if (A.length < 2) return A.length;
		int idx = 1;//Don't touch the first element!!
		for (int i = 1; i < A.length; i++) {//Don't touch the first element!!
			if (A[i] != A[idx])
				A[idx++] = A[i];//idx++ here, so return idx in the end, not idx+1
			A[i++] = 0;
		}
		return idx;
	}
	
	/*
	 * Remove elements so that each element can appear at most 2 times.
	 */
	public static int removeDuplicatesII(int[] A){
		if (A.length < 3) return A.length;
		int idx = 2;
		for (int i = 2; i < A.length; i++) {
			if (A[i] != A[i-2])
				A[idx++] = A[i];
		}
		return idx;
	}
	public static void main(String args[]) {
		int[] A = {1,1,2,2,3,3,3,4};
		//int len = RemoveDuplicates.removeDuplicates(A);
		int len = RemoveDuplicates.removeDuplicatesII(A);
		for (int i = 0; i < len; i++) {
			System.out.println(A[i]);
		}
	}
}

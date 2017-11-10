package arrays;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jan 7, 2015
Problem:    Merge Sorted Array
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/merge-sorted-array/
Notes:
Given two sorted integer arrays A and B, merge B into A as one sorted array.
Note:
You may assume that A has enough space to hold additional elements from B. 
The number of elements initialized in A and B are m and n respectively.
Solution: From back to forth.
*/

import java.util.Arrays;

public class MergeSortedArray {
	public void mergeSortedArray(int[] A, int m, int[] B, int n) {
		int i = m - 1;
		int j = n - 1;
		int x = m + n - 1;
		while (i >= 0 && j >= 0) {
			if (A[i] >= B[j]) A[x--] = A[i--];
			else A[x--] = B[j--];
		}
		while (j >= 0) A[x--] = B[j--];
	}
	
	public static void main(String args[]) {
		int[] A = {3,5,7,10,15,0,0,0,0,0,0,0};
		int[] B = {1,2,6,9,13,33};
		new MergeSortedArray().mergeSortedArray(A, 5, B, 6);
		System.out.println(Arrays.toString(A));
	}
}

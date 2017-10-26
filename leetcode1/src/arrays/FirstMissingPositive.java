package arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    First Missing Positive
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/first-missing-positive/
Notes:
Given an unsorted integer array, find the first missing positive integer.
For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.
Your algorithm should run in O(n) time and uses constant space.
Solution: Although we can only use constant space, we can still exchange elements within input A!
          Swap elements in A and try to make all the elements in A satisfy: A[i] == i + 1.
          Pick out the first one that does not satisfy A[i] == i + 1.
*/

public class FirstMissingPositive {
	public static int firstMissingPositive_1(int[] A) {
		for (int i = 0; i < A.length; i++)
			if (A[i] <= 0) A[i] = A.length + 2;
		for (int i = 0; i < A.length; i++) {
			if (Math.abs(A[i]) < A.length + 1) {
				int cur = Math.abs(A[i]) - 1;
				A[cur] = -Math.abs(A[cur]);
			}
		}
		for (int i = 0; i < A.length; i++)
			if(A[i] > 0) return i+1;
		return A.length + 1;
	}
	
	public static int firstMissingPositive_2(int A[], int n) {
		for (int i = 0; i < n; i++) {
			while (A[i] >= 1 && A[i] <= n && A[i] != A[A[i]-1]) {
				System.out.println("**"+A[i]);
				int tmp = A[i];
				A[i] = A[tmp - 1];
				A[tmp - 1] = tmp;
			}
		}
		int i = 0;
		for (i = 0; i < n; i++) {
			if (A[i] != i + 1) {
				System.out.println(A[i]+",i="+i);
				break;
			}
		}
		return i + 1;
	}
	
	public static void main(String args[]) {
		int[] A = {-1, -2, 0, 4, 5, 3, 2, 7, 1};
		//System.out.println(FirstMissingPositive.firstMissingPositive_1(A));
		//Don't do the 2 function at the same time. A[] will be modified in the first function.
		System.out.println(FirstMissingPositive.firstMissingPositive_2(A, A.length));
	}
}

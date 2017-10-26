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
	public static void main(String args[]) {
		System.out.println("test");
	}
}

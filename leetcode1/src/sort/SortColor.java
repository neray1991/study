package sort;

import java.util.Arrays;


/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 24, 2013
 Problem:    Sort Colors
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/sort-colors/
 Notes:
 Given an array with n objects colored red, white or blue, sort them so that objects of the same color
 are adjacent, with the colors in the order red, white and blue.
 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 Note:
 You are not suppose to use the library's sort function for this problem.
 Follow up:
 A rather straight forward solution is a two-pass algorithm using counting sort.
 First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with 
 total number of 0's, then 1's and followed by 2's.
 Could you come up with an one-pass algorithm using only constant space?
 Solution: 0 0 0 1 1 1 1 ...... 2 2 2 2
               |         |      |
             zero        i     two
              ->        ->     <-  
 */

public class SortColor {
	public static void sortColor(int[] A) {
		int n = A.length;
		for (int i = 0, left = 0, right = n - 1;i <= right;) {
			if (A[i] == 0) {
				A[i++] = A[left]; //Swap A[i] and A[left] here, think about it, A[left] here can only be 1 if left != i;
				A[left++] = 0;
			} else if (A[i] == 2) {
				A[i] = A[right];
				A[right--] = 2;
			} else {
				i++;
			}
		}
	}
	/*
	 Author:     King, wangjingui@outlook.com
	 Date:       Dec 24, 2013
	 Problem:    Sort Colors
	 Difficulty: Medium
	 Source:     http://lintcode.com/zh-cn/problem/sort-colors-ii/
	 Notes:
	 Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.
	 Note
	 You are not suppose to use the library's sort function for this problem.
	 Example
	 GIven colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4]. 
	 Challenge
	 A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory.
	 Can you do it without using extra memory?
	 Solution: Use the first k buckets to store the count. (count sort, two pass).
	 */
	public static void sortColorII(int[] A, int k) {
		int n = A.length;
		if (n <= 1) return;
		for (int i = 0; i < n; i++) {
			if (A[i] > 0) { //Careful, A[i] must > 0 here!
				int c = A[i];
				A[i] = 0;
				while (true) {
					if (A[c-1] <= 0) { // (numbered from 1 to k) that's why we need to use c-1 here.
						A[c-1]--;
						break;
					} else {
						int col = A[c-1];
						A[c-1] = -1;
						c = col;
					}
				}
			}
		}
		int idx = n - 1;
		for (int j = k - 1; j >= 0; j--) {
			while (A[j] < 0) {
				A[idx--] = j + 1;
				if (j != 0)
					A[j]++; //A[0] will be the wrong value in here if there is no if.
			}
		}
	}
	public static void main(String args[]) {
		int[] A = {2,1,0,1,0,2,0,1,0,2,2,1,0};
		SortColor.sortColor(A);
		System.out.println(Arrays.toString(A));
		int[] B = {1,2,4,2,3,4,4,3,2,1,1,2,2,3,4,4,3,1};
		SortColor.sortColorII(B, 4);
		System.out.println(Arrays.toString(B));
	}
}

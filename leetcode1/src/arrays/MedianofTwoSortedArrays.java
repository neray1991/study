package arrays;

import java.util.Arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 12, 2014
Problem:    Median of Two Sorted Arrays
Difficulty: Hard
Source:     http://leetcode.com/onlinejudge#question_4
Notes:
There are two sorted arrays A and B of size m and n respectively. 
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
Solution: 1. O(m+n)
          2. O(log(m+n))
*/

public class MedianofTwoSortedArrays {
	public static int medianofTwoSortedArrays(int[] num1, int[] num2) {
		int n1 = num1.length;
		int n2 = num2.length;
		int total = n1 + n2;
		int k = total / 2;
		if ((total & 1) == 1) return findKthRe(num1, num2, k+1, 0, n1 - 1, 0, n2 - 1);
		else return (findKthRe(num1, num2, k, 0, n1 - 1, 0, n2 - 1) + findKthRe(num1, num2, k+1, 0, n1 - 1, 0, n2 - 1))/2;
	}
	
	public static int findKthRe(int[] A, int[] B, int k, int astart, int aend, int bstart, int bend) {
		int alen = aend - astart + 1;
		int blen = bend - bstart + 1;
		if (k == 1) return Math.min(A[astart], B[bstart]); //Here we can see that Kth means real Kth, k=1 means 1st. k=0 will not happen.
		if (alen > blen) return findKthRe(B, A, k, bstart, bend, astart, aend);
		if (alen == 0) return B[bstart + k - 1];//So it's k - 1 here.
		int sa = Math.min(A.length - 1, k/2);
		int sb = k - sa;
		if (A[astart+sa-1] == B[bstart+sb-1]) return A[astart+sa-1];
		if (A[astart+sa-1] > B[bstart+sb-1]) return findKthRe(A, B, k - sb, astart, aend, bstart + sb, bend);
		else return findKthRe(A, B, k - sa, astart + sa, aend, bstart, bend);
	}
	
	public static void main(String args[]) {
		int[] num1 = {1,2,3,4,5,10,15};
		int[] num2 = {8,10,11};
		System.out.println(MedianofTwoSortedArrays.medianofTwoSortedArrays(num1, num2));
	}
}

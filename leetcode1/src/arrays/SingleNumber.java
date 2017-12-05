package arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Jan 3, 2015
Problem:    Single Number
Difficulty: Easy
Source:     http://oj.leetcode.com/problems/single-number/
Notes:
Given an array of integers, every element appears twice except for one. 
Find that single one.
Your algorithm should have a linear runtime complexity. 
Could you implement it without using extra memory?
Solution: XOR.
*/

public class SingleNumber {
	public static int singleNumber(int[] A) {
		int res = 0;
		for (int i : A) {
			res = res ^ i;
		}
		return res;
	}
	

	/*
	 Author:     King, wangjingui@outlook.com
	 Date:       Jan 3, 2015
	 Problem:    Single Number II
	 Difficulty: Easy
	 Source:     http://oj.leetcode.com/problems/single-number-ii/
	 Notes:
	 Given an array of integers, every element appears three times except for one. 
	 Find that single one.
	 Your algorithm should have a linear runtime complexity. Could you implement it 
	 without using extra memory?
	 Solution: 1. Count the number of each bit.
	        2. We can improve this based on the previous solution using three bitmask variables.
	        3. An excellent answer by @ranmocy in LeetCode Discuss:
	        https://oj.leetcode.com/discuss/857/constant-space-solution?show=2542#a2542
	*/
	public static int singleNumberII_1 (int[] A) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			int one = 0;
			for (int k : A) {
				if (((k >> i) & 1) == 1) ++one;
			}
			res = res | ((one % 3) << i);
		}
		return res;
	}
	
	public static int singleNumberII_2 (int[] A) {
		int one = 0, twice = 0;
		for (int num : A) {
			twice = twice | (one & num);
			one = one ^ num;	//When num appear twice, this line will make the bitmask = 0 in one.
			int three = one & twice;
			one = one ^ three; //Clear up the three bitmask on both one
			twice = twice ^ three;//and twice
		}
		return one;
	}
	
	public static int singleNumberII_3(int[] A) {
		int k = 1, n = 3;
		int[] x = new int[n];
		x[0] = ~0;
		for (int num : A) {
			int t = x[n - 1];
			for (int i = n - 1; i >= 1; i--) {
				x[i] = (x[i - 1] & num) | (x[i] & ~num);
			}
			x[0] = (t & num) | (x[0] & ~num);
		}
		return x[k];
	}
	
	public static void main(String args[]) {
		int[] A = {1,1,2,3,3,4,4,5,5};
		System.out.println(SingleNumber.singleNumber(A));
		int[] B = {1,1,1,2,3,3,3,4,4,4,5,5,5};
		System.out.println(SingleNumber.singleNumberII_3(B));
	}
}

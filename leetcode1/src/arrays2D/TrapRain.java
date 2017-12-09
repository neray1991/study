package arrays2D;

/*
Author:     King, higuige@gmail.com
Date:       Oct 07, 2014
Problem:    Trapping Rain Water
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/trapping-rain-water/
Notes:
Given n non-negative integers representing an elevation map where the width of 
each bar is 1, compute how much water it is able to trap after raining.
For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
Solution: 1. Find left bound and right bound for each element. O(n).
          2. more space efficiency. Time: O(n), Space: O(1);
*/

public class TrapRain {
	public static int trapRain(int num[]) {
		if (num.length <= 2) return 0;
		int n = num.length;
		int max = 0, res = 0, left = 0, right = 0;
		for (int i = 0; i < n; i++) {
			if (num[i] > num[max]) max = i;
		}
		for (int i = 1; i <= max; i++) {
			if (num[i] > num[left]) {
				left = i;
			} else { 
				res += num[left] - num[i];
			}
		}
		for (int i = n - 1; i >= max; i--) {
			if (num[i] > num[right]) {
				right = i;
			} else {
				res += num[right] - num[i];
			}
		}
		return res;
	}
	
	public static int trapRain_0(int[] num) {
		if (num.length <=2) return 0;
		int n = num.length;
		int maxleft = 0, maxright = n - 1, left = 0, right = n - 1, res = 0;
		while (left <= right) {
			if (num[maxleft] <= num[maxright]) {
				if (num[left] >= num[maxleft]) {
					maxleft = left;
				} else {
					res += num[maxleft] - num[left];
				}
				left++;
			} else {
				if (num[right] >= num[maxright]) {
					maxright = right;
				} else {
					res += num[maxright] - num[right];
				}
				right--;
			}
		}
		return res;
	}
	
	public static void main(String args[]) {
		int[] num = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(TrapRain.trapRain(num));
		System.out.println(TrapRain.trapRain_0(num));
	}
}

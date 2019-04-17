package jumpGame;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 21, 2014
Problem:    Jump Game II
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/jump-game-ii/
Notes:
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.
For example:
Given array A = [2,3,1,1,4]
The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
Solution: Jump to the position where we can jump farthest (index + A[index]) next time.
*/

public class JumpGameII {
	public static int jumpGame_0 (int[] A) {
		int n = A.length;

		int last = 0, cur = 0, res = 0;
		for (int i = 0; i < n; i++) {
			if (i > last) {//last indicates the farthest pos last step can reach.
				res++;
				last = cur;
				if (cur >= n - 1) return res;
			}
			cur = Math.max(cur, A[i] + i);
		}
		return res;//This function cannot return 0 if the end point cannot be reached.
	}
	public static int jumpGame(int[] input) {
		int n = input.length, steps = 0, pos = 0, s = input[0];
		while (pos < n - 1 && input[pos] > 0) {
			s = 0;
			int i = input[pos], tmp = 0;
			for (; i > 0; i--) {
				if (pos + i >= n - 1) {
					tmp = i;
					break;
				}
				if (input[pos + i] + i > s) {
					s = input[pos + i] + i;
					tmp = i;
				}
			}
			pos += tmp;
			//System.out.println("**"+pos);
			steps++;
		}
		if (pos >= n - 1) return steps;
		return 0;
	}
}

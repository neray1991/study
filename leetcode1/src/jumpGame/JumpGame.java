package jumpGame;

import java.util.*;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 21, 2014
Problem:    Jump Game
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/jump-game/
Notes:
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.
For example:
A = [2,3,1,1,4], return true.
A = [3,2,1,0,4], return false.
Solution: Updated solution: try every reachable index. 
          Thank to Wenxin Xing for kindly feedback and pointing out my big mistake:)
*/

public class JumpGame {
	public static boolean jumpGame(int[] input) {
		int n = input.length;
		int pos = 0;
		for (int i = 0; i < n; i++)
			if (pos >= i)
				pos = Math.max(input[i] + i, pos);
		return pos >= n - 1;
	}
	
	public static void main(String args[]) {
		int[] input = dataStructures.RandomStruct.getRandomArray(10, 4);
		System.out.println(Arrays.toString(input));
		System.out.println(JumpGame.jumpGame(input));
		System.out.println(JumpGameII.jumpGame(input));
		System.out.println(JumpGameII.jumpGame_0(input));
	}
}

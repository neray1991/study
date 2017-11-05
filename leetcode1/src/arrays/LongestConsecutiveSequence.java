package arrays;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jun 18, 2014
Problem:    Longest Consecutive Sequence
Difficulty: Hard
Source:     https://oj.leetcode.com/problems/longest-consecutive-sequence/
Notes:
Given an unsorted array of integers, find the length of the longest consecutive 
elements sequence.
For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
Your algorithm should run in O(n) complexity.
Solution 1: Update solution. 
*/

import java.util.*;

public class LongestConsecutiveSequence {
	public static int longestConsecutiveSequence(int[] num) {
		int size = num.length;
		int res = 0;
		HashMap<Integer, Integer> unmap = new HashMap<Integer, Integer>();
		for (int i = 0; i < size; i++) {
			int val = num[i];
			if (unmap.containsKey(val)) continue;
			if (unmap.containsKey(val - 1) && unmap.containsKey(val + 1)) {
				unmap.put(val, unmap.get(val - 1) + unmap.get(val + 1) + 1);
				unmap.put(val - unmap.get(val - 1), unmap.get(val));
				unmap.put(val + unmap.get(val + 1), unmap.get(val));
			} else if (unmap.containsKey(val - 1)) {
				unmap.put(val, unmap.get(val - 1) + 1);
				unmap.put(val - unmap.get(val - 1), unmap.get(val));
			} else if (unmap.containsKey(val + 1)) {
				unmap.put(val, unmap.get(val + 1) + 1);
				unmap.put(val + unmap.get(val + 1), unmap.get(val));
			} else {
				unmap.put(val, 1);
			}
			res = Math.max(res, unmap.get(val));
		}
		return res;
	}
	
	public static void main(String args[]) {
		int[] input = {101, 102, 103, 99, 100, 4, 200, 1, 3, 2};
		System.out.println(LongestConsecutiveSequence.longestConsecutiveSequence(input));
	}
}

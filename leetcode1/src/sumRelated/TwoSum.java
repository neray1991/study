package sumRelated;


/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Dec 13, 2014
 Problem:    Two Sum
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/two-sum/
 Notes:
 Given an array of integers, find two numbers such that they add up to a specific target number.
 The function twoSum should return indices of the two numbers such that they add up to the target, 
 where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 You may assume that each input would have exactly one solution.
 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2
 Solution: 1. Hash table. O(n)
           
 Note:  Hash Table solution has been updated.  In case that the two elements are the same, 
        all the indices should be stored in the map.
 */

import java.util.*;

public class TwoSum {
	public static int[] twoSum(int[] A, int target) {
		int n = A.length;
		if (n < 2) return null;
		int[] res = new int[2];
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			if (map.containsKey(A[i]) == false) {
				map.put(target - A[i], i);
			} else {
				res[0] = map.get(A[i]) + 1;//Look at the question, you need to add one here
				res[1] = i + 1;//And here
				return res;
			}
		}
		return null;
	}
	
	public static void main(String args[]) {
		int[] A = {2,7,11,15};
		System.out.println(Arrays.toString(TwoSum.twoSum(A, 9)));
	}
}

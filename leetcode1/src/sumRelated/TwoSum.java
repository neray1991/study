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
	
	/*
	Given an array of integers, find how many pairs in the array such that 
	their sum is bigger than a specific target number. Please return the number of pairs.
	Example
	numbers=[2, 7, 11, 15], target=24
	return 1
	Challenge
	Either of the following solutions are acceptable:
	O(1) Space, O(nlogn) Time
	Tags Expand 
	Two Pointers
	*/
	public static int SumII(int[] A, int target) {
		int count = 0, n = A.length;
		int cur = n - 1;
		while (cur > 0) {
			int left = 0;
			int right = cur - 1;
			int mid = (left + right) / 2;
			do {
				if (A[mid] + A[cur] <= target)
					left = mid + 1;
				else 
					right = mid - 1;
			} while (right > left);
			if (left > cur - 1) break;
			System.out.println(cur - left);
			count += cur - left;
			cur--;
		}
		return count;
	}
	
	
	public static void main(String args[]) {
		int[] A = {2,7,11,15};
		System.out.println(Arrays.toString(TwoSum.twoSum(A, 9)));
		System.out.println(TwoSum.SumII(A, 13));
	}
}

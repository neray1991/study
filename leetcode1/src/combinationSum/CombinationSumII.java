/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Combination Sum II
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/combination-sum-ii/
 Notes:
 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations
 in C where the candidate numbers sums to T.
 Each number in C may only be used once in the combination.
 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, .. , ak) must be in non-descending order. (ie, a1 <= a2 <= ... <= ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
 A solution set is: 
 [1, 7] 
 [1, 2, 5] 
 [2, 6] 
 [1, 1, 6] 
 Solution: ..Similar to Combination Sum I, except for line 42 && 44.
 */

package combinationSum;

import java.util.*;

class SolutionII {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		Arrays.sort(candidates);
		combinationSumRe(candidates, 0, target, path, res);
		return res;
	}
	void combinationSumRe(int[] candidates, int start, int target, List<Integer> path, List<List<Integer>> res) {
		if (target == 0) {
			List<Integer> p = new ArrayList<Integer>(path);
			res.add(p);
			return;
		}
		for (int i = start; i < candidates.length; i++) {
			path.add(candidates[i]);
			combinationSumRe(candidates, i+1, target - candidates[i], path, res);
			path.remove(path.size() - 1);
		}
	}
	
}

public class CombinationSumII {
	public static void main(String args[]) {
		int[] candidates = {2, 5, 7, 10, 4, 3, 6, 11, 8};
		List<List<Integer>> res = new SolutionII().combinationSum(candidates, 20);
		for (List<Integer> path : res)
			System.out.println(path);
	}
}

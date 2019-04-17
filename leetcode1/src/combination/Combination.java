/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Combinations
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/combinations/
 Notes:
 Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 For example,
 If n = 4 and k = 2, a solution is:
 [
    [2,4],
    [3,4],
    [2,3],
    [1,2],
    [1,3],
    [1,4],
 ]
 Solution: DFS.
 */

package combination;

import java.util.*;

class Solution {
	public List<List<Integer>> combination(int n, int k) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> comb = new ArrayList<Integer>();
		combinationRe(1, n, k, comb, res);
		return res;
	}
	void combinationRe(int start, int n, int m, List<Integer> comb, List<List<Integer>> res) {
		if (m == 0) {
			List<Integer> tmp = new ArrayList<Integer>(comb);
			res.add(tmp);
			return;
		}
		for (int i = start; i <= n; i++) {
			comb.add(i);
			combinationRe(i + 1, n, m - 1, comb, res);
			//This line is important in recursive. Must have RETREAT!
			comb.remove(comb.size() - 1);
		}
	}
}

public class Combination {
	public static void main(String args[]) {
		System.out.println(new Solution().combination(5, 4));
	}
}

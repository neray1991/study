package calculate;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Permutation Sequence
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/permutation-sequence/
Notes:
The set [1,2,3,...,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.
Note: Given n will be between 1 and 9 inclusive.
Solution: 1. Brute!
          2. combinatorial mathematics.
*/

import java.util.*;

public class PermutationSeq {
	public static String permutationSeq (int n, int k) {
		StringBuffer sb = new StringBuffer();
		StringBuffer res = new StringBuffer();
		int total = 1;
		for (int i = 1; i <= n; i++) {
			total *= i;
			sb.append(i);
		}
		k--;
		while (n > 0) {
			total = total / n;
			int idx = k / total;
			res.append(sb.charAt(idx));
			k = k % total;
			sb.deleteCharAt(idx);
			n--;
		}
		return res.toString();
	}
	
	public static void nextPermutation(char[] num) {
		int last = num.length - 1;
		int i = last;
		while (i > 0 && num[i-1] >= num[i]) --i;
		for (int l = i, r = last; l < r; l++, --r) {
			num[l] = (char) (num[l] ^ num[r]);
			num[r] = (char) (num[l] ^ num[r]);
			num[l] = (char) (num[l] ^ num[r]);
		}
		if (i == 0) {
			return;//We get the largest one.	
		}
		int j = i;
		while (j <= last && num[i-1] >= num[j]) j++;
		num[i-1] = (char) (num[i-1] ^ num[j]);
		num[j] = (char) (num[i-1] ^ num[j]);
		num[i-1] = (char) (num[i-1] ^ num[j]);
	}
	
	public static String getPermutation_1(int n, int k) {
		char[] num = new char[n];
		for (int i = 0; i < n; i++) num[i] = (char)(i + '1');
		while (--k > 0) {//Careful here, k must minus 1 before the first loop.
			nextPermutation(num);
		}
		return String.valueOf(num);
	}
	
	/*
	 Author:     King, wangjingui@outlook.com
	 Date:       Dec 25, 2014
	 Problem:    Permutations II
	 Difficulty: Easy
	 Source:     https://oj.leetcode.com/problems/permutations-ii/
	 Notes:
	 Given a collection of numbers that might contain duplicates, return all possible unique permutations.
	 For example,
	 [1,1,2] have the following unique permutations:
	 [1,1,2], [1,2,1], and [2,1,1].
	 Solution: dfs...
	 */
	public static boolean nextPermutation(int[] num) {
		int last = num.length - 1;
		int i = last;
		while (i > 0 && num[i-1] >= num[i]) --i;
		for (int l = i, r = last; l < r; l++, --r) {
			num[l] = (char) (num[l] ^ num[r]);
			num[r] = (char) (num[l] ^ num[r]);
			num[l] = (char) (num[l] ^ num[r]);
		}
		if (i == 0) {
			return false;//We get the largest one.	
		}
		int j = i;
		while (j <= last && num[i-1] >= num[j]) j++;
		num[i-1] = (char) (num[i-1] ^ num[j]);
		num[j] = (char) (num[i-1] ^ num[j]);
		num[i-1] = (char) (num[i-1] ^ num[j]);
		return true;
	}
	
	public static List<List<Integer>> permute(int[] num) {
		if (num.length == 0) return null;
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(num);
		do {
			List<Integer> tmp = new ArrayList<Integer>();
			for (int i : num) tmp.add(i);
			res.add(tmp);
		} while (nextPermutation(num));
		return res;
	}

	public static List<List<Integer>> permute_2(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		boolean[] free = new boolean[num.length];
		Arrays.fill(free, true);
		permuteRe(num, res, path, free);
		return res;
	}
	
	public static void permuteRe(int[] num, List<List<Integer>> res, List<Integer> path, boolean[] free) {
		if (num.length == path.size()) {
			List<Integer> tmp = new ArrayList<Integer>(path);
			res.add(tmp);
			return;
		}
		for (int i = 0; i < num.length; i++) {
			if (free[i] == true) {
				free[i] = false;
				path.add(num[i]);
				permuteRe(num, res, path, free);
				free[i] = true;
				path.remove(path.size()-1);
			}
		}
	}
	
	public static void main(String args[]) {
		System.out.println(PermutationSeq.getPermutation_1(9, 100000));
		System.out.println(PermutationSeq.permutationSeq(9, 100000));
		int[] num = {2,2,1,2};
		System.out.println(PermutationSeq.permute(num));
		System.out.println(PermutationSeq.permute_2(num));//This function cannot distinguish identical numbers.
	}
}

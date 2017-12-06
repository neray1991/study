package arrays;


/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Nov 18, 2014
 Problem:    Subsets
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/subsets/
 Notes:
 Given a set of distinct integers, S, return all possible subsets.
 Note:
 Elements in a subset must be in non-descending order.
 The solution set must not contain duplicate subsets.
 For example,
 If S = [1,2,3], a solution is:
 [
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
 ]
 Solution: 1. Updated Iterative solution.
           2. Updated Recursive solution.
 */

import java.util.*;

public class Subsets {
	public static List<List<Integer>> subsets(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(new ArrayList<Integer>());
		Arrays.sort(num);
		for (int i = 0; i < num.length; i++) {
			int k = res.size();//This is important, otherwise the following loop will not end.
			for (int j = 0; j < k; j++) {
				List<Integer> tmp = new ArrayList<Integer>(res.get(j));
				tmp.add(num[i]);
				res.add(tmp);
			}
		}
		return res;
	}
	
	public static void subsetsRe(int[] num, List<List<Integer>> res) {
		if (num.length == 0) return;
		int k = res.size();
		for (int i = 0; i < k; i++) {
			List<Integer> tmp = new ArrayList<Integer>(res.get(i));
			tmp.add(num[0]);
			res.add(tmp);
		}
		subsetsRe(Arrays.copyOfRange(num, 1, num.length), res);
	}
	
	public static List<List<Integer>> subsets_1(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(new ArrayList<Integer>());
		Arrays.sort(num);
		subsetsRe(num, res);
		return res;
	}
	
	public static void subsetRe_0(int[] num, int start, List<List<Integer>> res, List<Integer> path) {
	//	if (start >= num.length) return;
		List<Integer> tmp = new ArrayList<Integer>(path);
		res.add(tmp);
		for (int i = start; i < num.length; i++) {
			path.add(num[i]);
			subsetRe_0(num, i + 1, res, path);
			path.remove(path.size() - 1);
		}
	}
	
	public static List<List<Integer>> subsets_0(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer> ();
		subsetRe_0(num, 0, res, path);
		return res;
	}
	
	/*
	 Author:     Andy, nkuwjg@gmail.com
	 Date:       Nov 18, 2014
	 Problem:    Subsets II
	 Difficulty: Easy
	 Source:     https://oj.leetcode.com/problems/subsets-ii/
	 Notes:
	 Given a collection of integers that might contain duplicates, S, return all possible subsets.
	 Note:
	 Elements in a subset must be in non-descending order.
	 The solution set must not contain duplicate subsets.
	 For example,
	 If S = [1,2,2], a solution is:
	 [
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	 ]
	 Solution: ..Similar to Subset I.
	 */
	public static List<List<Integer>> subsetsII(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(new ArrayList<Integer>());
		Arrays.sort(num);
//		int presize = 0;
		int dup = 0;
		for (int i = 0; i < num.length; i++) {
			int k = res.size();//This is important, otherwise the following loop will not end.
			if (i > 0 && num[i] == num[i-1]) {
				dup++;
			} else {
				dup = 0;
			}
			for (int j = 0; j < k; j++) {
				
				/*This is not working when duplicates are 3 or more*/
			//	if (i > 0 && num[i] == num[i-1]) {
				//	if (tmp.size() == 0 || tmp.get(tmp.size() - 1) != num[i]) continue; //Careful, tmp.size() may == 0 here.
			//	}
				/*This is not working when duplicates are 3 or more*/
		/*		if (i == 0 || num[i] != num[i-1] || j > presize) {
					List<Integer> tmp = new ArrayList<Integer>(res.get(j));
					tmp.add(num[i]);
					res.add(tmp);
				}*/
				List<Integer> tmp = new ArrayList<Integer>(res.get(j));
				if (dup > 0 && (tmp.size() < dup || tmp.get(tmp.size() - dup) != num[i])) {
					System.out.println(tmp.size()+"dup="+dup+","+tmp);
					continue;
				}
				tmp.add(num[i]);
				res.add(tmp);
			}
//			presize = k;
		}
		return res;
	}
	
	public static void subsetIIRe_0(int[] num, int start, List<List<Integer>> res, List<Integer> path) {
	//	if (start >= num.length) return;
		List<Integer> tmp = new ArrayList<Integer>(path);
		res.add(tmp);
		for (int i = start; i < num.length; i++) {
			if (i > start && num[i] == num[i-1]) //Skip directly, cause it has been counted before.
				continue;
			path.add(num[i]);
			subsetIIRe_0(num, i + 1, res, path);
			path.remove(path.size() - 1);
		}
	}
	
	public static List<List<Integer>> subsetsII_0(int[] num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer> ();
		subsetIIRe_0(num, 0, res, path);
		return res;
	}
	
	public static void main(String args[]) {
		int[] num = {1,2,3,4};
		System.out.println(Subsets.subsets(num));
		System.out.println(Subsets.subsets_1(num));
		System.out.println(Subsets.subsets_0(num));
		int[] num2 = {1,2,2,2,2,3};
		System.out.println(Subsets.subsetsII(num2));
		System.out.println(Subsets.subsetsII_0(num2));
	}
}

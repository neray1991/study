/*
+ Author:     King, wangjingui@outlook.com
+ Date:       Dec 20, 2014
+ Problem:    4Sum
+ Difficulty: Medium
+ Source:     https://oj.leetcode.com/problems/4sum/
+ Notes:
+ Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
+ Find all unique triplets in the array which gives the sum of zero.
+ Note:
+ Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
+ Find all unique quadruplets in the array which gives the sum of target.
+ Note:
+ Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a <= b <= c <= d)
+ The solution set must not contain duplicate quadruplets.
+ For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
+ A solution set is:
+ (-1,  0, 0, 1)
+ (-2, -1, 1, 2)
+ (-2,  0, 0, 2)
+
+ Solution: Similar to 3Sum, 2Sum.
+ */

package sumRelated;

import java.util.*;

public class FourSum {
	public List<List<Integer>> fourSum(int[] num, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int N = num.length;
		if (N < 4) return res;
		Arrays.sort(num);
		for (int i = 0; i < N - 4; i++)
		{
			if (i > 0 && num[i] == num[i-1]) continue;
			for (int j = i + 1; j < N - 3; j++)
			{
				if (j > i + 1 && num[j] == num [j - 1]) continue;
				int twosum = target - num[i] - num[j];
				int k = j + 1, l = N - 1;
				while(k < l)
				{
					int sum = num[k] + num[l];
					if (sum == twosum) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(num[i]);tmp.add(num[j]);tmp.add(num[k]);tmp.add(num[l]);
						res.add(tmp);
						while (k < l && num[k] == num[k+1]) k++;
						while (l > k && num[l] == num[l-1]) l--;
						k++;l--;
					} 
					else if (sum > twosum) k++;
					else l--;
				}
			}
		}
		return res;
	}
	
	public static void main(String args[]) {
		int[]input = {1,2,3,4,5,-1,-5,0,-50};
		List<List<Integer>> result = new FourSum().fourSum(input, 5);
		System.out.println(result);
	}
}






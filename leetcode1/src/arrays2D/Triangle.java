package arrays2D;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       May 14, 2013
Problem:    Triangle
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/triangle/
Notes:
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
For example, given the following triangle
[
   [2],
  [3,4],
 [6,5,7],
[4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number 
of rows in the triangle.
Solution: Note that there are n elements in the n-th row (n starts from 1).
          1. DP. Do not need additional spaces (happen in-place).
*/

import java.util.*;

public class Triangle {
	public static int minPath(List<List<Integer>> triangle) {
		int n = triangle.size();
		List<Integer> dp = new ArrayList<Integer>();
		dp.add(triangle.get(0).get(0));
		for (int i = 1; i < n; i++) {
			List<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j <= i; j++) {
				if (j == 0) {
					tmp.add(dp.get(0) + triangle.get(i).get(j));
					continue;
				}
				if (j == i) {
					tmp.add(dp.get(i - 1) + triangle.get(i).get(j));
					continue;
				}
				tmp.add(Math.min(dp.get(j), dp.get(j-1)) + triangle.get(i).get(j));
			}
			dp = tmp;
	//		System.out.println(dp);
		}
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < dp.size(); i++) {
			if (dp.get(i) < res) res = dp.get(i);
		}
		return res;
	}
	
	public static int minPath_0(List<List<Integer>> triangle) {
		for (int i = triangle.size() - 2; i >= 0; --i) {
			List<Integer> cur = triangle.get(i);
			List<Integer> next = triangle.get(i+1);
			for (int j = 0; j < i + 1; j++) {
				cur.set(j,  Math.min(next.get(j), next.get(j+1)) + cur.get(j));
			}
		}
		return triangle == null ? 0 : triangle.get(0).get(0);
	}
	
	public static void main(String args[]) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		Integer[] tmp = new Integer[] {2};
		triangle.add(Arrays.asList(tmp));
		tmp = new Integer[] {3,4};
		triangle.add(Arrays.asList(tmp));
		tmp = new Integer[] {6,5,7};
		triangle.add(Arrays.asList(tmp));
		tmp = new Integer[] {4,1,8,3};
		triangle.add(Arrays.asList(tmp));
		System.out.println(Triangle.minPath(triangle));
		System.out.println(Triangle.minPath_0(triangle));
	}
}

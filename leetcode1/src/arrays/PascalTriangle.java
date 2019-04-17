package arrays;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Apr 16, 2013
Problem:    Pascal's Triangle
Difficulty: Easy
Source:     http://leetcode.com/onlinejudge#question_118
Notes:
Given numRows, generate the first numRows of Pascal's triangle.
For example, given numRows = 5,
Return
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
Solution: .....
*/

import java.util.*;

public class PascalTriangle {
	public static List<List<Integer>> pascalTriangle(int n) {
		if (n < 1) return null;
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(new ArrayList<Integer>(1));
		for (int i = 1; i < n; i++) {
			List<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < i + 1; j++) {//Next line is only one element more than the previous one.
				if (j == 0 || j == i) {
					tmp.add(1);
				}
				else {
					tmp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
				}
			}
			res.add(tmp);
		}
		return res;
	}
	
	/*getRow returns the kth row of the Pascal's triangle
	 * do it in O(k) extra space.
	*/
	public static List<Integer> getRow(int k) {
		if (k < 1) return null;
		List<Integer> res = new ArrayList<Integer>();
		res.add(1);
		for (int i = 1; i < k; i++) {
			for (int j = i - 1; j > 0; j--)// Must iterate from the end.
				res.set(j, res.get(j - 1) + res.get(j));
			res.add(1);
		}
		return res;
		
	}
	
	public static void main(String args[]) {
		System.out.println(PascalTriangle.pascalTriangle(5));
		System.out.println(PascalTriangle.getRow(20));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);


	}
}

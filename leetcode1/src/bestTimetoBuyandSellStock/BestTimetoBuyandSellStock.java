/*
 Author:     King, higuige@gmail.com
 Date:       Oct 07, 2014
 Problem:    Best Time to Buy and Sell Stock
 Difficulty: Easy
 Source:     http://leetcode.com/onlinejudge#question_121
 Notes:
 Say you have an array for which the ith element is the price of a given stock on day i.
 If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
 design an algorithm to find the maximum profit.
 Solution: For each element, calculate the max difference with the former elements.
 */

package bestTimetoBuyandSellStock;

import java.util.*;

class Solution {
	ArrayList<Integer> maxDiffwithFormer(int[] input) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(0);
		int minVal = input[0];
		if (input.length < 2) return res;
			for (int i = 1; i < input.length; i++) {
				res.add(input[i] - minVal);
				minVal = Math.min(input[i], minVal);
			}
		return res;
	}
}

public class BestTimetoBuyandSellStock {
	public static void main(String args[]) {
		int[] input = {0, 1, 6, 8, 4, 10, 15, 20, 9, -1, -5, 10, 17};
		System.out.println(new Solution().maxDiffwithFormer(input));
		ArrayList<Integer> result = new Solution().maxDiffwithFormer(input);
		Object [] output = result.toArray();
		Arrays.sort(output);
		System.out.println(output[output.length - 1]);
	}
}

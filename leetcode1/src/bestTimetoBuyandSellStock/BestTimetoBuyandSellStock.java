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

/*
Author:     King, wangjingui@outlook.com
Date:       Jan 02, 2015
Problem:    Best Time to Buy and Sell Stock II
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
Notes:
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time 
(ie, you must sell the stock before you buy again).
Solution: 1. At the beginning of the ascending order: buy.
             At the ending of the ascending order: sell.
*/

class Solution2 {
	int growthInAll (int[] input) {
		int sum = 0;
		if (input.length < 1) return sum;
		for (int i = 1; i < input.length; i++)
			if (input[i] - input[i-1] > 0)
				sum += input[i] - input[i-1];
		return sum;
	}
}

/*
Author:     King, wangjingui@outlook.com
Date:       Jan 02, 2015
Problem:    Best Time to Buy and Sell Stock III
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
Notes:
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.
Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
Solution: dp. max profit =  max { l2r[0...i] + r2l[i+1...N-1] }.
                        0 <= i <= N-1
*/

class Solution3 {
	int oneTransaction (int[] input) {
		if (input.length < 1) return 0;
		int minVal = input[0], res = 0;
		for (int i = 0; i < input.length; i++) {
			res = Math.max(res, input[i]-minVal);
			if (input[i] < minVal)
				minVal = input[i];
		}
		return res;
	}
	int twoTransactions (int[] input) {
		int res = 0;
		for (int i=0; i<input.length; i++) {
			res = Math.max(res, oneTransaction(Arrays.copyOfRange(input, 0, i)) + oneTransaction(Arrays.copyOfRange(input, i+1, input.length)));
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
		
		System.out.println(new Solution2().growthInAll(input));
		System.out.println(new Solution3().oneTransaction(input));
		System.out.println(new Solution3().twoTransactions(input));
		
	}
}

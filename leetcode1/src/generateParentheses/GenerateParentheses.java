package generateParentheses;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Generate Parentheses
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/generate-parentheses/
Notes:
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:
"((()))", "(()())", "(())()", "()(())", "()()()"
Solution: Place n left '(' and n right ')'.
          Cannot place ')' if there are no enough matching '('.
*/

import java.util.*;

public class GenerateParentheses {
	public static void generateParenthesesRe(ArrayList<String> res, int left, int right, String str) {
		if (left > 0)
			generateParenthesesRe(res, left - 1, right, str.concat("("));
		if (right > left)
			generateParenthesesRe(res, left, right - 1, str + ")");
		if (left == right && left == 0)
			res.add(str);
		return; 
	}
	
	public static void main(String args[]) {
		ArrayList<String> res = new ArrayList<String>();
		String str = new String();
		GenerateParentheses.generateParenthesesRe(res, 3, 3, str);
		System.out.println(res);
	}
}

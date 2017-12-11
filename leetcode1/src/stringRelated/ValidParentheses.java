package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Valid Parentheses
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/valid-parentheses/
Notes:
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
Solution: stack.
*/

import java.util.*;

public class ValidParentheses {
	public static boolean isValidParentheses(String s) {
		Stack<Character> stk = new Stack<Character>();
		for (int i = 0; i < s.length(); i ++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
				stk.push(s.charAt(i));
			else {
				if (stk.isEmpty() || s.charAt(i) - stk.pop() > 2)
					return false;
			}
		}
		return stk.isEmpty();
	}
	
	public static void main(String args[]) {
		System.out.println('[' - 0);
		System.out.println(']' - 0);
		System.out.println(ValidParentheses.isValidParentheses("({}[]){[]}"));
	}
}

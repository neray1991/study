package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Letter Combinations of a Phone Number
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/letter-combinations-of-a-phone-number/
Notes:
Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
Solution: ...
*/

import java.util.*;

public class LetterComboofPhoneNumber {
	static String[] keyboard = new String[]{" ","","abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	public static List<String> letterComboofPhoneNumber(String input) {
		List<String> res = new ArrayList<String>();
		int i = 0;
		while (i < input.length()) {
			int num = input.charAt(i) - '0';
			i++;
			if (num == 1) continue;
			if (res.isEmpty()) {
				for (int j = 0; j < keyboard[num].length(); j++)
					res.add(""+keyboard[num].charAt(j));
			}
			else {
				List<String> tmp = new ArrayList<String>();
				while (!res.isEmpty()) {
					for (int j = 0; j < keyboard[num].length(); j++)
						tmp.add(res.get(0)+keyboard[num].charAt(j));
					res.remove(0);
				}
				res = tmp;
			}
		}
		return res;
	}
	
	public static void main(String args[]) {
		String input = "23019";
		System.out.println(LetterComboofPhoneNumber.letterComboofPhoneNumber(input));
	}
}

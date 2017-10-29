package calculate;

/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 15, 2014
 Problem:    Fraction to Recurring Decimal
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/fraction-to-recurring-decimal/
 Notes:
 Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 If the fractional part is repeating, enclose the repeating part in parentheses.
 For example,
 Given numerator = 1, denominator = 2, return "0.5".
 Given numerator = 2, denominator = 1, return "2".
 Given numerator = 2, denominator = 3, return "0.(6)".
 Solution: ...
 */

import java.util.*;

public class FractiontoDecimal {
	public static String fractiontoDecimal (int num, int den) {
		if (den == 0) return new String("NaN");
		if (num == 0) return new String("0");
		StringBuffer res = new StringBuffer();
		if ((num > 0) ^ (den > 0)) res.append('-');
		num = Math.abs(num);
		den = Math.abs(den);
		int tmp = num / den;
		num = num % den;
		res.append(tmp);
		if (num == 0) return res.toString();
		res.append('.');
		int dot = res.length();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (num != 0) {
			if (map.containsKey(num)) {
				System.out.println("**"+num);
				dot = map.get(num);
				res.insert(dot, '(');
				res.append(')');
				break;
			}
			map.put(num, dot++); //This line should be here. put num before we calculate new num. dot indicate the position just behind . in the beginning, so it should be dot++ instead of ++dot.
			int tmp2 = num * 10 / den;
			res.append(tmp2);
			num = num * 10 % den;
			System.out.println("*"+num);	
		}
		return res.toString();
	}
	
	public static void main(String args[]) {
		System.out.println(FractiontoDecimal.fractiontoDecimal(-41, 30));
	}
}

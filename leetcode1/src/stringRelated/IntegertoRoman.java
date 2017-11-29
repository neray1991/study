package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 13, 2014
Problem:    Integer to Roman
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/integer-to-roman/
Notes:
Given an integer, convert it to a roman numeral.
Input is guaranteed to be within the range from 1 to 3999.
Solution: Buffer the roman numbers.
*/

import java.util.*;

public class IntegertoRoman {
	public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };  
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int i = 0;
        StringBuffer sb = new StringBuffer();
        while (num != 0) {
        	int count = num / values[i];
        	num = num % values[i]; 
        	while (count > 0) {
        		sb.append(numerals[i]);
        		count--;
        	}
        	i++;
        }
        return sb.toString();
	}
	
	public static int romanToInt(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('M', 1000);
		map.put('D', 500);
		map.put('C', 100);
		map.put('L', 50);
		map.put('X', 10);
		map.put('V', 5);
		map.put('I', 1);
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1)))
				res -= map.get(s.charAt(i));
			else res += map.get(s.charAt(i));
		}
		return res;
	}
	
	public static void main(String args[]) {
		String roman = IntegertoRoman.intToRoman(2912);
		System.out.println(roman);
		int integer = IntegertoRoman.romanToInt(roman);
		System.out.println(integer);
	}
}

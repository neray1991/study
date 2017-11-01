package largestNumber;

/*
Author:     King, nkuwjg@gmail.com
Date:       Jan 13, 2015
Problem:    ZigZag Conversion
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/largest-number/
Notes:
Given a list of non negative integers, arrange them such that they form the largest number.
For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
Note: The result may be very large, so you need to return a string instead of an integer.
Solution: ...
*/

import java.util.*;
//import dataStructures.RandomStruct;

public class LargestNumber {
	public static String largestNumber(int[] input) {
		String[] str = new String[input.length];
		for (int i = 0; i < input.length; i++)
			str[i] = Integer.toString(input[i]);
		Arrays.sort(str, new Comparator<String>() {
			//@Override
			public int compare(String s1, String s2) {//We duplicate the string while we compare, eg: "34"->"3434" compare with "343"->"343343"
				int res = 0;
				for (int i = 0; i < Math.min(s1.length(), s2.length())*2; i++) {
					if (i >= s1.length())
						res = s1.charAt(i - s1.length()) - s2.charAt(i);
					else if (i >= s2.length())
						res = s1.charAt(i) - s2.charAt(i - s2.length());
					else
						res = s1.charAt(i) - s2.charAt(i);
					if (res != 0) return res;
				}
				return 0;
			}
		});
		StringBuffer res = new StringBuffer();
		for (int i = str.length - 1; i >= 0; i--)
			res.append(str[i]);
		return res.toString();
	}
	
	public static String largestNumber_1(int[] num) {
		int size = num.length;
		if (size <= 0) return new String();
		if (size == 1) return String.valueOf(num[0]);
		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				String aa = ""+a+b;
				String bb = ""+b+a;
				return bb.compareTo(aa);
			}
		};
		Integer[] in = new Integer[size];
		for (int i = 0; i < size; i++)
			in[i] = Integer.valueOf(num[i]);
		Arrays.sort(in,comp);
		StringBuffer res = new StringBuffer();
		int i = 0;
		while ((i < in.length - 1) && (in[i] == 0)) ++i; //In case the array is full of "0"
		while (i < in.length) res.append(in[i++]);
		return res.toString();
	}
	
	public static void main(String args[]) {
		//int[] input = RandomStruct.getRandomArray(30, 300);
		int[] input = {3434, 20, 343, 5, 9, 0};
		System.out.println(LargestNumber.largestNumber(input));
		System.out.println(LargestNumber.largestNumber_1(input));
	}
}

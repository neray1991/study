package calculate;


/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Multiply Strings
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/multiply-strings/
 Notes:
 Given two numbers represented as strings, return multiplication of the numbers as a string.
 Note: The numbers can be arbitrarily large and are non-negative.
 Solution: Just like what we do when multiplying integers.
 */

public class MultiplyString {
	public static String multiplyString(String num1, String num2) {
		int l1 = num1.length(), l2 = num2.length(), cur = 0;
		StringBuffer res = new StringBuffer();
		for (int i = l1 + l2 - 2; i >= 0; i--) {
			for (int j = Math.min(i, l1 - 1); j >= 0; j--) {
				System.out.println("i="+i+", j="+j);
				if (i - j > l2 - 1) break;
				int tmp = (num1.charAt(j) - '0') * (num2.charAt(i-j) - '0');
				cur += tmp;
			}
			res.insert(0, cur%10);
			cur = cur / 10;
		}
		return res.toString();
	}
	
	public static void main(String args[]) {
		String num1 = "987";
		String num2 = "654";
		System.out.println(MultiplyString.multiplyString(num1, num2));
	}
}

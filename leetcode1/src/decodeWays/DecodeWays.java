/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 25, 2014
 Problem:    Decode Ways
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/decode-ways/
 Notes:
 A message containing letters from A-Z is being encoded to numbers using the following mapping:
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given an encoded message containing digits, determine the total number of ways to decode it.
 For example,
 Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 The number of ways decoding "12" is 2.
 Solution: 1. dp. Time : O(n); Space : O(1).
 */

package decodeWays;

public class DecodeWays {
	public static int numDecodings(String s) {
		if (s.length() == 0 || s.charAt(0) == '0') return 0;
		int N = s.length();
		int f0 = 0, f1 = 1;
		for (int i = 1; i < N; i++) {
			if (s.charAt(i) == '0') f1 = 0;
			int num = s.charAt(i) - '0' + (s.charAt(i - 1) - '0') * 10;
			/*f1 means ways up to ith char, f0 means ways up to i-1th char*/
			if (num < 10 || num > 26)
				f0 = 0;
			int tmp = f1;
			f1 = f1 + f0;
			f0 = tmp;
		}
		return f1;
	}
	
	public static void main(String args[]) {
		//String input = "123456789120102345673213";
		String input = "121020982231";//if nothing goes wrong, this will be a fibonacci series
		System.out.println(DecodeWays.numDecodings(input));
	}
}

package stringRelated;

import java.util.Arrays;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Dec 12, 2014
Problem:    Longest Substring Without Repeating Characters
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
Notes:
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
For "bbbbb" the longest substring is "b", with the length of 1.
Solution: 1. Pay attention when moving the 'start' pointer forward.
          2. More space, but maybe faster.
*/

public class NoneRepeatSubstr {
	public static int lengthofLongestSubstring_1 (String s) {
		boolean[] hash = new boolean[256];
		Arrays.fill(hash, false);
		int n = s.length();
		if (n <= 1) return n;
		int start = 0, end = 0, res = 0;
		while (end < n && start + res < n) {
			if (hash[s.charAt(end)] == false)
				hash[s.charAt(end++)] = true;
			else
				hash[s.charAt(start++)] = false; //Move until the duplicate char is cleared to false.
			res = Math.max(res, end - start);
		}
		return res;
	}
	
	public static int lengthofLongestSubstring_2(String s) {
		int[] hash = new int[256];
		Arrays.fill(hash, -1);
		int n = s.length();
		if (n <= 1) return n;
		hash[s.charAt(0)] = 0;
		int start = 0, res = 1, cur = 0;
		while (++cur < n) {
			if (hash[s.charAt(cur)] >= start) {//hash[s.charAt(cur)]>0 means s.charAt(cur) has occurred before.
				start = hash[s.charAt(cur)] + 1;//start indicates the place where s.charAt(cur) occurs last time + 1.
			}
			res = Math.max(res, cur - start + 1);//start from the one after last duplicate position. THIS IS THE KEY TO THIS PROBLEM.
			hash[s.charAt(cur)] = cur; //The element in hash[] is this. 
		}
		return res;
	}
	
	public static void main(String args[]) {
		String s = "abcadebbbbabcdddcbcacba";
		System.out.println(NoneRepeatSubstr.lengthofLongestSubstring_1(s));
		System.out.println(NoneRepeatSubstr.lengthofLongestSubstring_2(s));
	}
}

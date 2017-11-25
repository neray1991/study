package stringRelated;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jan 16, 2015
Problem:    Restore IP Addresses
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/restore-ip-addresses/
Notes:
Given a string containing only digits, restore it by returning all possible valid IP address combinations.
For example:
Given "25525511135",
return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
Solution: DFS.
*/

import java.util.*;

public class RestoreIPAddress {
	public static List<String> restoreIPAddress (String s) {
		List<String> res = new ArrayList<String>();
		String pattern = "";
		restoreIPRe(s, 4, res, pattern); //num is the key here. num indicates how many parts left to restore.
	//	dfs(s, new String(), 0, 0, res);
		return res;
	}
	
	public static void restoreIPRe(String s, int num, List<String> res, String pattern) {
		if (num <= 0 || s.length() == 0)  {
			return;
		}
		if (num == 1) {

			int tmp = Integer.parseInt(s);
			if (tmp < 256 && s.charAt(0) != '0' || s == "0") {
				res.add(pattern.substring(1) + "." + s);
			}
			return;
		}
		num--; //Don't do num-- in the function parameter, num will keep minus one for 3 times!.
		restoreIPRe(s.substring(1), num, res, pattern + "." + s.substring(0, 1));
		if (s.charAt(0) == '0') return;
		if (s.length() > 2) 
			restoreIPRe(s.substring(2), num, res, pattern + "." + s.substring(0, 2));
		if (s.length() > 3 && Integer.parseInt(s.substring(0, 3)) < 256)
			restoreIPRe(s.substring(3), num, res, pattern + "." + s.substring(0, 3));
	}
	
	public static void dfs(String s, String ip, int start, int step, List<String> res) {
		if (step == 4 && start == s.length()) {
			res.add(ip);
		}
		if (step == 4) return;
		if (s.length() - start > (4 - step) * 3) return;
		if (s.length() - start < 4 - step) return;
		if (ip.length() != 0) ip += ".";
		int num = 0;
		for (int i = start; i < start + 3 && i < s.length(); ++i) {
			num = num * 10 + s.charAt(i) - '0';
			if (num > 255) break;
			ip += s.charAt(i);
			dfs(s, ip, i + 1, step + 1, res);
			if (num == 0) break;//IP address can be zero, but we have to prevent 255.25.5.0135 this thing happens.
		}
	}
	
	public static void main(String args[]) {
		//String s = "25525511135";
		String s = "2552550135";
		//System.out.println(s.substring(3));
		System.out.println(RestoreIPAddress.restoreIPAddress(s));
		String t = "01";
		System.out.println(t.substring(0, 1)); //Hey, t.substring(0, 1) == "0" is false!
	}
}

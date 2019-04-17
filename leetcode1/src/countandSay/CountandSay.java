/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Count and Say
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/count-and-say/
 Notes:
 The count-and-say sequence is the sequence of integers beginning as follows:
 1, 11, 21, 1211, 111221, ...
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth sequence.
 Note: The sequence of integers will be represented as a string.
 Solution: ...
*/

package countandSay;

//import java.util.*;

public class CountandSay {
	public static String countandSay(int n) {
		StringBuffer s = new StringBuffer("1");
		StringBuffer res = new StringBuffer();
		while((--n) != 0) {
			System.out.println("n="+n);
			res.setLength(0);
			int size = s.length();
			int cnt = 1;
			char cur = s.charAt(0);
			for (int i = 1; i < size; i++) {
				if (s.charAt(i) != cur) {
					res.append(cnt);
					res.append(cur);
					cur = s.charAt(i);
					cnt = 1;
				} else cnt++;
 			}
			res.append(cnt);
			res.append(cur);
			//StringBuffer tmp = s;  Both ways work;
			s = res;
			//res = tmp;
			res = new StringBuffer();
		}
		return s.toString();
	}
	
	public static void main(String args[]) {
		System.out.println(CountandSay.countandSay(10));
	}
}

package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 13, 2014
Problem:    Reverse Words in a String 
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/reverse-words-in-a-string/
Notes:
Given an input string, reverse the string word by word.
For example,
Given s = "the sky is blue",
return "blue is sky the".
Solution: 1. Reverse the raw string and reverse each word.
          2. Cannot do it In Place by Java. oops~.~
*/

public class ReverseWords {
	public static String reverseWords(String s) {
		if (s == null) return null;
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			res.append(s.substring(s.length() - i - 1, s.length() - i));
		}
		System.out.println(res);
		int start = 0, end = 0;
		while (true) {
			while (end < res.length() && res.charAt(end) != ' ')
				end++;
			int space = end;
			while (start < --end) {
				char tmp = res.charAt(start);
				res.replace(start, start + 1, res.charAt(end)+""); //don't use start++ here, start will +1 at the end of this line!!!
				res.replace(end, end + 1, tmp+"");
				start++;
			}
			if (space == s.length()) //space == s.length()
				return res.toString();
			//start = space + 1; end = space + 1; Careful! What if there are more than one space?
			while (res.charAt(space) == ' ') space++;
			start = space; end = space;
		}
	}
	
	public static String reverseWords_0(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = s.length() - 1; i >= 0;) {
			while (i >= 0 && s.charAt(i) == ' ') i--;
			StringBuffer tmp = new StringBuffer();
			while (i >= 0 && s.charAt(i) != ' ') {
				tmp.append(s.charAt(i--));
			}
			tmp.reverse();
			if (sb.length() > 0 && tmp.length() > 0) sb.append(" ");
			sb.append(tmp);
		}
		return sb.toString();
	}
	
	public static void main(String args[]) {
		String s = "the sky is blue";
		System.out.println(ReverseWords.reverseWords(s));
		System.out.println(ReverseWords.reverseWords_0(s));
	}
}

package stringRelated;


/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Dec 25, 2014
 Problem:    Minimum Window Substring
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/minimum-window-substring/
 Notes:
 Given a string S and a string T, find the minimum window in S which will contain all the 
 characters in T in complexity O(n).
 For example,
 S = "ADOBECODEBANC"
 T = "ABC"
 Minimum window is "BANC".
 Note:
 If there is no such window in S that covers all characters in T, return the empty string "".
 If there are multiple such windows, you are guaranteed that there will always be only one unique 
 minimum window in S.
 Solution: 1. Use two pointers: start and end. 
              First, move 'end'. After finding all the needed characters, move 'start'.
           2. Use array as hashtable.
 */

public class MinWindowSubstring {
	public static String minWindowSubstring(String S, String T) {
		int N = S.length(), M = T.length();
		if (N < M) return new String("");
		int[] need = new int[256];
		int[] find = new int[256];
		for (int i = 0; i < M; i++)
			need[T.charAt(i)]++;
		
		int count = 0, resStart = -1, resEnd = N;
		for (int start = 0, end = 0; end < N; end++) {
			if (need[S.charAt(end)] == 0)
				continue;
			if (find[S.charAt(end)] < need[S.charAt(end)])
				count++;
			find[S.charAt(end)]++;
			if (count >= M) {
				while (start < end) {
					if(need[S.charAt(start)] != 0 && find[S.charAt(start)] == need[S.charAt(start)]) //Remember the case when need[S.charAt(start) == 0]. It should be excluded in the very beginning.
						break;
					if(find[S.charAt(start)] > need[S.charAt(start)])
						find[S.charAt(start)]--;
					start++;
				}
				System.out.println("start="+start+", end="+end);
				if ((end - start) < (resEnd - resStart)){
					resStart = start; 
					resEnd = end;
				}
			}
		}
		if (resStart >= 0)
			return S.substring(resStart, resEnd + 1);//The last char of String.substring is String.charAt[endIndex - 1], so we need to add one here.
		return new String("");
	}
	
	public static void main(String args[]) {
		String S = "ADOBECODEBANC";
		String T = "ABC";
		System.out.println(MinWindowSubstring.minWindowSubstring(S, T));
	}
}

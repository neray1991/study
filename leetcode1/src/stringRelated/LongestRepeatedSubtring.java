package stringRelated;

import java.util.*;

public class LongestRepeatedSubtring {
	public static String lcp(String s, String t) {
		int n = Math.min(s.length(), t.length());
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) != t.charAt(i))
				return s.substring(0, i);
		}
		return s.substring(0, n);
	}
	
	public static String lrs(String s) {
		int N = s.length();
		String[] suffixes = new String[N];
		for (int i = 0; i < N; i++)
			suffixes[i] = s.substring(i, N);
		
		Arrays.sort(suffixes);
		
		String lrs = "";
		for (int i = 0; i < N - 1; i++) {
			String x = lcp(suffixes[i], suffixes[i+1]);
			if (x.length() > lrs.length()) lrs = x;
		}
		return lrs;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		String s = sc.next();
		s = s.replaceAll(" ", "");
		System.out.println(s);
		System.out.println("'" + lrs(s) + "'");
	}
}

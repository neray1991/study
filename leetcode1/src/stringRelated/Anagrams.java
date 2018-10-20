/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 25, 2014
 Problem:    Anagrams
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/anagrams/
 Notes:
 Given an array of strings, return all groups of strings that are anagrams.
 Note: All inputs will be in lower-case.
 Solution: Sort the string to see if they're anagrams.
 */

package stringRelated;

import java.util.*;
import java.util.Map.Entry;

public class Anagrams {
	public static ArrayList<String> anagrams(String[] str) {
		ArrayList<String> res = new ArrayList<String>();
		HashMap<String, ArrayList<String>> group = new HashMap<String, ArrayList<String>>();
		if (str.length == 0) return res;
		for (int i = 0; i < str.length; i++) {
			char[] tmp = str[i].toCharArray();
			Arrays.sort(tmp);
			String s = String.valueOf(tmp);
			if (group.containsKey(s))
				(group.get(s)).add(str[i]);
			else {
				ArrayList<String> t = new ArrayList<String>();
				t.add(str[i]);
				group.put(s, t);
			}
		}
		Iterator<Entry<String, ArrayList<String>>> itr = group.entrySet().iterator();
		while(itr.hasNext()) {
			Entry<String, ArrayList<String>> entry = itr.next();
			ArrayList<String> val = entry.getValue();
			if (val.size() > 1)
				res.addAll(val);
		}
		return res;
	}
	
	public static void main(String args[]){
		String[] input = {"aadbc",
		                  "bcaad",
		                  "bring",
		                  "dog",
		                  "god",
		                  "ringb",
		                  "abadc",
		                  "gdo",
		                  "aaaad"};
		ArrayList<String> res = anagrams(input);
		System.out.println(res);
	}
}
